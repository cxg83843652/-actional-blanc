package cn.actional.phoneix.common.utils;

import cn.actional.phoneix.common.enums.ResponseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/25/20 8:47 PM
 *   统一ajax响应对象  常量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse extends HashMap<String,Object> {
    private Integer code;
    private String message;

    /**
     *  请求成功方法
     * @return  200
     */
    public static BaseResponse success() {
        return new BaseResponse(ResponseTypeEnum.REQUEST_SUCCESS.getCode(),ResponseTypeEnum.REQUEST_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse put(String key,Object val) {
        super.put(key, val);
        return this;
    }

    /**
     *  退出登录成功方法
     * @return  200
     */
    public static BaseResponse success(String message) {
        return new BaseResponse(ResponseTypeEnum.REQUEST_SUCCESS.getCode(),message);
    }




    public static BaseResponse loginError() {
        return new BaseResponse(ResponseTypeEnum.USER_INPUT_ERROR.getCode(),ResponseTypeEnum.USER_INPUT_ERROR.getMessage());
    }

}
