package cn.actional.phoneix.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/25/20 9:05 PM
 *      定义响应状态码和默认消息
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseTypeEnum {

    /**
     *
     */
    REQUEST_SUCCESS(200,"success"),
    USER_INPUT_ERROR(400,"用户名或密码错误"),
    NOT_FOUND(404,"请求资源不存在"),
    UNAUTHORIZED(401,"权限不足"),
    TOKEN_EXPIRED(402,"令牌过期，或被篡改"),
    INTERNAT_SERVER_ERROR(500,"服务器内部异常");

    private Integer code;
    private String message;





}
