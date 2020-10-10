package cn.actional.blanc.admin.controller.system;

import cn.actional.blanc.system.constant.CommonConstant;
import cn.actional.blanc.common.utils.BaseResponse;
import cn.actional.blanc.system.web.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 3:22 PM
 */
@RestController
@RequestMapping("/sys/user")
public class SysLoginController {

    private SysLoginService sysLoginService;

    @Autowired
    public void setSysLoginService(SysLoginService sysLoginService) {
        this.sysLoginService = sysLoginService;
    }

    @PostMapping("login")
    @ResponseBody
    public BaseResponse login(@RequestBody Map<String,String> user) {
        // 生成jwt令牌
        String token = sysLoginService.login(user.get("username"),user.get("password"));

        return BaseResponse.success().put(CommonConstant.TOKEN,token);
    }

    @PostMapping("info")
    @ResponseBody
    public BaseResponse info(@RequestBody String token) {
        System.out.println(token);
        return BaseResponse.success();
    }



}
