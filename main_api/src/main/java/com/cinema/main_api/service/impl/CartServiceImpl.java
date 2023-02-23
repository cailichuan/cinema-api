package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.CartService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Arrangement;
import model.entity.Cart;
import model.entity.Film;
import model.entity.User;
import model.vo.CartVo;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;


    @Resource
    private RedisUtils redisUtils;



    @Override
    public void save(Cart cart,String uuid) throws Exception {

        writeApi.saveCart(cart,uuid);
    }

    @Override
    public void deleteAllByUserId(Long uid,String uuid) {


    }

    @Override
    public List<CartVo> findAllByUserId(Long uid) throws ClassNotFoundException {
        //生成key和hashkey
        String key = RedisTable.SET.USER_ID_BY_CART_ID+uid;

        if (redisUtils.sSize(key)==0){
            log.info("查询数据库");
            List<CartVo> cartVos = readApi.findAllCartVoByUid(uid);

            log.info("存入缓存");
            for (CartVo cartVo : cartVos) {
                Film film = cartVo.getFilm();
                Arrangement arrangement = cartVo.getArrangement();
                Cart cart = cartVo.getCart();

                redisUtils.hPutIfAbsent(RedisTable.HASH.CART,String.valueOf(cart.getId()),String.valueOf(cart));
                if(film!=null)redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                if(arrangement!=null)redisUtils.hPutIfAbsent(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));

                redisUtils.sAdd(key,String.valueOf(cart.getId()));
            }

            return cartVos;
        }

        log.info("查询缓存");

        //查询user
        User user=null;
        Object UO = redisUtils.hGet(RedisTable.HASH.USER, String.valueOf(uid));
        if (UO==null){
            //查询数据库
            user = readApi.findUserById(uid);
            if (user == null){
                redisUtils.delete(key);
                return null;
            }
            redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(uid),String.valueOf(user));
        }
        else user = new RDTCUitl<User>().getData(UO,RDTCUitl.ClassName.USER);

        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> cartIds = PatternUtil.getStringNumLong(String.valueOf(objects));

        List<CartVo> cartVos = new ArrayList<>();

        for (Long cartId : cartIds) {

            //获取cart
            Object CO = redisUtils.hGet(RedisTable.HASH.CART, String.valueOf(cartId));
            Cart cart = null;
            if (CO==null){
                cart=readApi.findCartById(cartId);
                if (cart==null){
                    redisUtils.sRemove(key,String.valueOf(cartId));
                    continue;
                }
                redisUtils.hPut(RedisTable.HASH.CART,String.valueOf(cartId),String.valueOf(cart));
            }
            else cart = new RDTCUitl<Cart>().getData(CO, RDTCUitl.ClassName.CART);

            //cart的uid!=uerid
            if (cart.getUid()!=uid){
                redisUtils.sRemove(key,String.valueOf(cartId));
                continue;
            }





            //获取arrangement
            Object AO = redisUtils.hGet(RedisTable.HASH.ARRANGEMENT, String.valueOf(cart.getAid()));
            Arrangement arrangement = null;
            if (AO==null){
                //缓存没有则查询数据库
               arrangement = readApi.findArrangementById(cart.getAid()).getArrangement();

                if (arrangement!=null){
                    redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));
                }
            }
            else arrangement = new RDTCUitl<Arrangement>().getData(AO, RDTCUitl.ClassName.ARRANGEMENT);


            //获取Film
            Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(arrangement.getFid()));
            Film film =null;
            if (FO==null) {
                //缓存没有则查询数据库
                film = readApi.findFilmById(arrangement.getFid());

                //数据库的数据不为空则存入缓存
                if (film!=null) {
                    redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
                }
            }

            else film=new RDTCUitl<Film>().getData(FO, RDTCUitl.ClassName.FILM);

            cartVos.add(new CartVo(film,arrangement,cart));
        }

        return cartVos;
    }

    @Override
    public void deleteCarts(List<Cart> carts,String uuid) {

    }


    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteCartById(id,uuid);
    }

    @Override
    public void settleCarts(List<Cart> carts,String uuid) {

    }
}
