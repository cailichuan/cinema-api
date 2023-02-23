package type;

/**
 * redis表名数据
 */
public final class RedisTable {













    public static final class LIST{










    }


    public static final class HASH{

        //============================主要表=======================
        public static final String USER="user";

        public static final String ACTIVITY="activity";

        public static final String ARRANGEMENT="Arrangement";

        public static final String FILM="film";

        public static final String LEAVINGMESSAGE="leavingMessage";


        public static final String CART = "cart";

        public static final String DAILY_WORK = "daily_work";

        public static final  String ORDEREXCEPTION="orderexception";

        public static final String ORDER="order";

        public static final String POSTER="poster";

        public static final String REGISTRATION = "registration";

        public static final String ROLE="role";

        public static final String WORKER = "worker";

        public static final String WORKEREVALUATE="workerevaluate";

        public static final String FILMEVALUATE="filmevaluate";

        public static final String UPLOAD = "upload";

        //===============================中间表=================




        //==============================其余表====================

        //写操作的标识id
        public static final String WRITE_CHARACTERISTIC = "write_characteristic";

        //订单处理的id，接口幂等性的处理
        public static final String PAY_ORDER = "pay_order";

        //写操作的异常存储（写标识的id-抛出的异常）
        public static final String WRITE_CHARACTERISTIC_By_ERROR = "write_characteristic_by_error";

    }

    public static final class SET{
        //=====================其余表===============================
        //film_regin -- film_id (set)
        public static final String FILM_REGIN = "film_regin";


        //film_type -- film_id  (set)
        public static final String FILM_TYPE = "film_type";



        //arrangement_id -- seats (set)
        public static final String SEATS_HAVE_SELECTED="seats_have_selected";

        //likename -- film_id (set)
        public static final String FILM_LIKE_NAME="film_like_name";

        //poster_boolean -- poster_id (set)
        public static final String POSTER_BOOLEAN_BY_POSTER_ID="poster_boolean_by_poster_id";




        //=========================中间表=================================

        //film_id -- arrangement_id (set)
        public static final String FILM_ID_BY_ARRANGEMENT_ID="arrangement_id_by_film_id";

        //user_id -- cart_id (set)
        public static final String USER_ID_BY_CART_ID = "user_id_by_cart_id";

        //user_id -- leavingMessage_id (set)
        public static final String USER_ID_BY_LEAVINGMESSAGE_ID="user_id_by_leavingMessage_id";

        //user_id -- order_id(set)
        public static final String USER_ID_BY_ORDER_ID="user_id_by_order_id";

        //worker_id -- role_id
        public static final String WORKER_ID_BY_ROLE_ID="worker_id_by_role_id";

        //worker_id -- workerevaluate_id
        public static final String WORKER_ID_BY_WORKEREVALUATE_ID="worker_id_by_workerevaluate_id";

        //film_id -- filmEvaluat_id
        public static final String FILM_ID_BY_FILMEVALUAT_ID="film_id_By_filmEvaluat_id";


    }




    public static final class Z_SET{
        //================================其余表================


        //电影名搜索key的热度表
        //likename -- hot
        public static final String LIKE_NAME_HOT = "like_name_hot";


        //film_id -- film_hot (one -- key --value) (zset)
        public static final String FilM_HOT = "film_hot";
    }
}
