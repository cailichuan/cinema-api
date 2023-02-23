package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.config.RabbitMqConfig;
import com.cinema.main_api.service.OrderService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.*;
import model.vo.OneArrangementVo;
import model.vo.OrderVo;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void create(Cart cart,String uuid) throws Exception {

        writeApi.saveOrder(cart,uuid);




    }

    @Override
    public Order pay(Long id) throws Exception {

        //检查幂等性，如果缓存中存在说明正在操作处理订单
        if (redisUtils.hExists(RedisTable.HASH.PAY_ORDER,String.valueOf(id)))
            throw new Exception("请勿重复操作订单");

        //写入幂等性数据,mq的消费者的service处理完则删除接口幂等性数据
        redisUtils.hPut(RedisTable.HASH.PAY_ORDER,String.valueOf(id),String.valueOf(id));




        // 关联数据的一个类，交换机无论有没有收到生产者发送的消息，都会返回这个对象
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());


        rabbitTemplate.convertAndSend(RabbitMqConfig.ORDER_EXCHANGE,RabbitMqConfig.ODER_ROUTE_KEY,String.valueOf(id),correlationData);

        //如果幂等性数据还存在则显示订单在支付中
        if (redisUtils.hExists(RedisTable.HASH.PAY_ORDER,String.valueOf(id))) {
            throw new Exception("订单正在支付中......");
        }


        return readApi.findOrderById(id);
    }

    @Override
    public void update(Order order,String uuid) throws Exception {

        writeApi.updateOrder(order,uuid);
    }

    @Override
    public List<OrderVo> findAll() throws ClassNotFoundException {
        //生成key
        String key = RedisTable.SET.USER_ID_BY_ORDER_ID;

        Set<String> tables = redisUtils.keys(key + "*");
        List<String> hasDataList = new ArrayList<>();
        

        //记录有数据的table
        for (String table : tables) {
            DataType type = redisUtils.type(table);
            if (type!= DataType.SET) continue;
            if (redisUtils.lLen(table) > 0) {
               
                hasDataList.add(table);
            }
            
        }


        if (hasDataList.size()==0)  {

            log.info("查询数据库");
            List<OrderVo> allOrderVo = readApi.findAllOrderVo();

            //写入缓存
            for (OrderVo orderVo : allOrderVo) {
                Order order = orderVo.getOrder();
                Film film = orderVo.getFilm();
                User user = orderVo.getUser();
                Arrangement arrangement = orderVo.getArrangement();


                //写入缓存
                redisUtils.hPutIfAbsent(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));
                if (user!=null){
                    redisUtils.sAdd(key+user.getId(),String.valueOf(order.getId()));
                    redisUtils.hPutIfAbsent(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
                };

                if (film!=null) redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                if (arrangement!=null) redisUtils.hPutIfAbsent(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));



            }

            return allOrderVo;
        }


        log.info("查询数据库");
        List<OrderVo> ODVS = new ArrayList<>();
        for (String setName : hasDataList) {

            String uid = PatternUtil.getRedisListKeyNumber(key,setName);

            if (uid==null) continue;

            //查看是否存在该uid的user,不存在则跳过循环
            User user=null;
            if (!redisUtils.hExists(RedisTable.HASH.USER,uid)){
                user = readApi.findUserById(Long.valueOf(uid));
                if (user==null) {
                    //删除uid-oid的list
                    redisUtils.delete(setName);
                   continue;
                }

                //写入缓存
                redisUtils.hPut(RedisTable.HASH.USER,uid,String.valueOf(user));
            }

            //获取uid对应的oid
            Set<Object> oidOs = redisUtils.setMembers(setName);
            List<Long>  oids= PatternUtil.getStringNumLong(String.valueOf(oidOs));


            for (Long oid : oids) {

                Object OO = redisUtils.hGet(RedisTable.HASH.ORDER, String.valueOf(oid));
                Order order = null;

                if (OO==null){
                    //查询数据库
                    order = readApi.findOrderById(Long.valueOf(oid));
                    if (order==null){
                        redisUtils.sRemove(setName,oid);

                        continue;
                    }

                    redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(oid),String.valueOf(order));

                }

                else order = new RDTCUitl<Order>().getData(OO,RDTCUitl.ClassName.ORDER);

                if (order.getUid() != Long.valueOf(uid)){
                    redisUtils.sRemove(setName,String.valueOf(oid));
                    continue;
                }

                Long aid=order.getAid();

                Object AO = redisUtils.hGet(RedisTable.HASH.ARRANGEMENT, String.valueOf(aid));
                Arrangement arrangement = null;
                Film film = null;
                if (AO==null) {
                    //缓存为空查询数据库
                    OneArrangementVo OAV = readApi.findArrangementById(aid);
                    arrangement = OAV.getArrangement();
                    film = OAV.getFilm();

                    if (OAV==null){
                        ODVS.add(new OrderVo(order,user,null,null));
                        continue;
                    }

                    redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));

                    if (film ==null) {
                        ODVS.add(new OrderVo(order,user,null,arrangement));
                        continue;
                    }
                    redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                    ODVS.add(new OrderVo(order,user,film,arrangement));
                    continue;
                }

                else arrangement = new RDTCUitl<Arrangement>().getData(AO,RDTCUitl.ClassName.ARRANGEMENT);

                Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(arrangement.getFid()));
                if (FO==null){
                    film=readApi.findFilmById(arrangement.getFid());
                    if (film==null){
                        ODVS.add(new OrderVo(order,user,null,arrangement));
                        continue;
                    }

                    redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                }

                else film=new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);


                //封装结果
                ODVS.add(new OrderVo(order,user,film,arrangement));

            }

        }

        return ODVS;
    }

    @Override
    public List<OrderVo> findByUser(Long uid) throws ClassNotFoundException {
        //生成key
        String key=RedisTable.SET.USER_ID_BY_ORDER_ID+uid;


        if (redisUtils.sSize(key)==0) {
            log.info("查询数据库");

            List<OrderVo> ODVs = readApi.findOrderVoByUserId(uid);

            for (OrderVo odv : ODVs) {

                User user = odv.getUser();
                Order order = odv.getOrder();
                Film film = odv.getFilm();
                Arrangement arrangement = odv.getArrangement();

                //写入缓存
                redisUtils.sAdd(key,String.valueOf(order.getId()));
                redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));
                if (user!=null) redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(uid),String.valueOf(user));
                if (film!=null) redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                if (arrangement!=null) redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));


            }

            return ODVs;

        }


        log.info("查询缓存");
        List<OrderVo> ODVs=new ArrayList<>();
        Set<Object> objects = redisUtils.setMembers(key);


        User user = null;
        Object UO = redisUtils.hGet(RedisTable.HASH.USER, String.valueOf(uid));

        if (UO==null) {
            user=readApi.findUserById(uid);
            if (user == null) {

                redisUtils.delete(key);
                return null;
            }

            redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(uid),String.valueOf(user));

        }

        else user=new RDTCUitl<User>().getData(UO,RDTCUitl.ClassName.USER);

        for (Object object : objects) {
            String oid = String.valueOf(object);


            Object OO = redisUtils.hGet(RedisTable.HASH.ORDER, oid);
            Order order = null;
            if (OO==null){
                //查询数据库
                order = readApi.findOrderById(Long.valueOf(oid));
                if (order==null){
                    redisUtils.sRemove(key,oid);
                    continue;
                }

                redisUtils.hPut(RedisTable.HASH.ORDER,oid,String.valueOf(order));

            }
            else order = new RDTCUitl<Order>().getData(OO,RDTCUitl.ClassName.ORDER);


            //oder的uid!=userid
            if (order.getUid() != uid){
                redisUtils.sRemove(key,oid);
                continue;
            }

            Long aid=order.getAid();

            //arrangement的处理
            Object AO = redisUtils.hGet(RedisTable.HASH.ARRANGEMENT, String.valueOf(aid));

            Arrangement arrangement = null;
            Film film = null;
            if (AO==null) {
                //缓存为空查询数据库
                OneArrangementVo OAV = readApi.findArrangementById(aid);
                arrangement = OAV.getArrangement();
                film = OAV.getFilm();
                if (arrangement==null){
                    ODVs.add(new OrderVo(order,user,null,null));
                    continue;
                }
                redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));

                if (film==null){
                    ODVs.add(new OrderVo(order,user,null,arrangement));
                    continue;
                }
                redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                ODVs.add(new OrderVo(order,user,film,arrangement));
                continue;
            }

            else arrangement = new RDTCUitl<Arrangement>().getData(AO,RDTCUitl.ClassName.ARRANGEMENT);



            //film的处理
            Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(arrangement.getFid()));
            if (FO==null){
                film=readApi.findFilmById(arrangement.getFid());
                if (film==null){
                    ODVs.add(new OrderVo(order,user,null,arrangement));
                    continue;
                }

                redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
            }

            else film=new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);




            ODVs.add(new OrderVo(order,user,film,arrangement));

        }

        return ODVs;
    }
}
