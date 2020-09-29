package cn.actional.phoneix.common.utils;

import lombok.Getter;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-09-29 15:03
 */
@Getter
public class BaseException extends RuntimeException {
    private String msg;
    private int code = 500;


    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public BaseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }


    public BaseException( String msg, int code,Throwable cause) {
        super(msg,cause);
        this.msg = msg;
        this.code = code;
    }
}
