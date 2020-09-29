package cn.actional.phoneix.controller.system;

import cn.actional.phoneix.common.utils.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 3:22 PM
 */
@RestController
public class SysLoginController {

    @PostMapping("/sys/login")
    @ResponseBody
    public BaseResponse login(@RequestBody Map<String,String> user) {
        System.out.println("=============================");
        return BaseResponse.success();
    }
}
