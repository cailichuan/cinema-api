package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.LeavingMessageService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Activity;
import model.entity.LeavingMessage;
import model.entity.User;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
@Service
@CacheConfig(cacheNames = "leavingMessage")
public class LeavingMessageServiceImpl implements LeavingMessageService {


    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(LeavingMessage leavingMessage,String uuid) throws Exception {

        writeApi.saveLeavingMessage(leavingMessage,uuid);

    }

    @Override
    public void reply(LeavingMessage leavingMessage,String uuid) throws Exception {

        writeApi.replyLeavingMessage(leavingMessage,uuid);
    }

    @Override
    public List<LeavingMessageVo> findAll() throws ClassNotFoundException {
        if (redisUtils.hSize(RedisTable.HASH.LEAVINGMESSAGE)==0){

            log.info("查询数据库");
            List<LeavingMessageVo> allLeavingMessageVo = readApi.findAllLeavingMessageVo();

            //存入缓存
            if (allLeavingMessageVo!=null){
                for (LeavingMessageVo leavingMessageVo : allLeavingMessageVo) {

                    LeavingMessage leavingMessage = leavingMessageVo.getLeavingMessage();
                    User user = leavingMessageVo.getUser();
                    redisUtils.hPutIfAbsent(RedisTable.HASH.LEAVINGMESSAGE,String.valueOf(leavingMessage.getId()),String.valueOf(leavingMessage));
                    if (user!=null)redisUtils.hPutIfAbsent(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
                }
            }

            return allLeavingMessageVo;
        }

        log.info("查询缓存");
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.LEAVINGMESSAGE);




        List<LeavingMessageVo> lVos=new ArrayList<>();
        for (Object object : objects) {

            LeavingMessage LM = new RDTCUitl<LeavingMessage>().getData(object, RDTCUitl.ClassName.LEAVINGMESSAGE);



            User user = null;
            Object UO = redisUtils.hGet(RedisTable.HASH.USER, String.valueOf(LM.getUid()));
            //为空则查询数据库
            if (UO==null) {
                user=readApi.findUserById(LM.getId());
                if (user!=null)redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
            }

            else user=new RDTCUitl<User>().getData(UO,RDTCUitl.ClassName.USER);


            lVos.add(new LeavingMessageVo(LM.getId(),LM,user));
        }


        return lVos;
    }


    /**
     *主要redis表：
     * 1.user连接 leavingMessage的list: user_id -- leavingMessage_id
     *
     * @return
     */

    @Override
    public List<ActiveUserVo> findActiveUsers() throws ClassNotFoundException {
        //生成key
        String key = RedisTable.SET.USER_ID_BY_LEAVINGMESSAGE_ID;

        if (redisUtils.hSize(RedisTable.HASH.USER) == 0){

            log.info("查询数据库");

            List<User> users = readApi.findAllUser();



            ArrayList<ActiveUserVo> AUVS=new ArrayList<>();

            for (User user : users) {
                Integer number=0;

                //将user写入缓存
                redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));

                //根据suerId查询leavingMessage
                List<LeavingMessage> LMS = readApi.findLeavingMessagesByUid(user.getId());

                if (LMS!=null) {

                    number=LMS.size();

                    //写入uid-lid的缓存list和写入留言的缓存
                    for (LeavingMessage lm : LMS) {
                        redisUtils.sAdd(key+user.getId(),String.valueOf(lm.getId()));

                        redisUtils.hPutIfAbsent(RedisTable.HASH.LEAVINGMESSAGE,String.valueOf(lm.getId()),String.valueOf(lm));
                    }
                }

                AUVS.add(new ActiveUserVo(user,number));
            }

            //按照留言数量排序
            AUVS.sort((v1,v2)-> v2.getNumber().compareTo(v1.getNumber()));

            return AUVS;
        }


        log.info("查询缓存");
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.USER);
        List<ActiveUserVo> AUVS= new ArrayList<>();

        for (Object object : objects) {

            User user = new RDTCUitl<User>().getData(object, RDTCUitl.ClassName.USER);
            String userId=String.valueOf(user.getId());

            //搜索缓存
            Set<Object> lMIdO = redisUtils.setMembers(key + userId);
            List<Long> LMIds = PatternUtil.getStringNumLong(String.valueOf(lMIdO));

            int size=0;


            for (Long lmId : LMIds) {

                Object LMO = redisUtils.hGet(RedisTable.HASH.LEAVINGMESSAGE, String.valueOf(lmId));
                LeavingMessage LM = null;
                if (LMO==null){
                    LM = readApi.findLeavingMessageById(lmId);
                    if (LM==null ){
                        redisUtils.sRemove(key+userId,String.valueOf(lmId));
                        continue;
                    }

                    redisUtils.hPut(RedisTable.HASH.LEAVINGMESSAGE,String.valueOf(lmId),String.valueOf(LM));

                }

                else LM=new RDTCUitl<LeavingMessage>().getData(LMO,RDTCUitl.ClassName.LEAVINGMESSAGE);


                if (LM.getUid() != user.getId()){
                    redisUtils.sRemove(key+userId,String.valueOf(lmId));
                    continue;
                }

                size++;
            }


           AUVS.add(new ActiveUserVo(user,size));
        }


        //按照留言数量排序
        AUVS.sort((v1,v2)-> v2.getNumber().compareTo(v1.getNumber()));
        return AUVS;
    }
}
