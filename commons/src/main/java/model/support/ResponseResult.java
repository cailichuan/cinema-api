package model.support;


/**
 * 返回数据的封装类
 * @param <T>
 */
public class ResponseResult <T>{

    //状态码
    private Integer code;


    //是否安全
    private boolean success;


    //附加信息
    private String msg;

    //返回的数据
    private Object data;

    public ResponseResult() {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = null;
    }

    //不附带msg返回数据
    public ResponseResult(T data){
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = data;
    }

    //默认附带msg返回数据
    public ResponseResult(String msg,T data){
        this.code = 200;
        this.success = true;
        this.msg = msg;
        this.data = data;
    }

    //默认的错误返回
    public ResponseResult(Integer code,String msg){
        this.code = code;
        this.success = false;
        this.msg = msg;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return (T)data;
    }
}
