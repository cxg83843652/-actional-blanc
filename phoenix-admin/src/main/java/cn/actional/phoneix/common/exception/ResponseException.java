package cn.actional.phoneix.common.exception;


import cn.actional.phoneix.common.utils.BaseResponse;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 12:39 AM
 */
public class ResponseException extends RuntimeException {
    private Integer code;
    private String message;


    public ResponseException(BaseResponse type, String message) {
        code = type.getCode();
        this.message = message;
    }
}
