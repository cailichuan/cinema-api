package util;

import cn.hutool.json.JSONUtil;
import org.apache.commons.collections4.Get;

/**
 *
 * 数据类型转换的类
 */
public final  class RDTCUitl <T>{

    

    


    public static final class ClassName{

        public static final String ENTITY = "model.entity.";

        public static final String DTO = "model.dto.";

        public static final String ACTIVITY=ENTITY+"Activity";

        public static final String ADMIN = ENTITY+"Admin";

        public static final String ARRANGEMENT = ENTITY+"Arrangement";

        public static final String CART = ENTITY+"Cart";

        public static final String DAILYWORK = ENTITY+"DailyWork";

        public static final String FILM = ENTITY+"Film";

        public static final String FILMEVALUATE = ENTITY+"FilmEvaluate";

        public static final String LEAVINGMESSAGE = ENTITY+"LeavingMessage";

        public static final String ORDER = ENTITY+"Order";

        public static final String ORDEREXCEPTION = ENTITY+"OrderException";

        public static final String POSTER = ENTITY+"Poster";

        public static final String REGISTRATION = ENTITY+"Registration";

        public static final String ROLE = ENTITY+"Role";

        public static final String UPLOAD = ENTITY+"Upload";

        public static final String USER  = ENTITY+"User";

        public static final String WORKER = ENTITY+"Worker";

        public static final String WORKEREVALUATE= ENTITY+"WorkerEvaluate";

        public static final String POSTERDTO = DTO+"PosterDto";

        public static final String REGISTERDTO = DTO+"RegisterDto";
    }


    public static  String getJson(String str){
        int index = str.lastIndexOf("{");
        String substring = str.substring(index);

        return substring;
    }

    public T getData(Object o,String className) throws ClassNotFoundException {

        String json = getJson(String.valueOf(o));

        Object o1 = JSONUtil.toBean(json, Class.forName(className));

        return (T)o1;


    }
    

}
