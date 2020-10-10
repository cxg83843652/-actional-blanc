package cn.actional.blanc.admin.controller.system;

import cn.actional.blanc.common.utils.BaseResponse;
import cn.actional.blanc.common.utils.QueryParam;
import cn.actional.blanc.system.web.entity.SysUserEntity;
import cn.actional.blanc.system.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-10 21:31:25
 */
@RestController
@RequestMapping("sysuser")
public class SysUserController {

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        QueryParam query = new QueryParam(params);
        return BaseResponse.success().put("page", query);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public BaseResponse info(@PathVariable("userId") Long userId) {
        SysUserEntity sysUser = sysUserService.getById(userId);

        return BaseResponse.success().put("sysUser", sysUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResponse save(@RequestBody SysUserEntity sysUser) {
        sysUserService.save(sysUser);

        return BaseResponse.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public BaseResponse update(@RequestBody SysUserEntity sysUser) {
        sysUserService.updateById(sysUser);

        return BaseResponse.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public BaseResponse delete(@RequestBody Long[] userIds) {
        sysUserService.removeByIds(Arrays.asList(userIds));

        return BaseResponse.success();
    }

}
