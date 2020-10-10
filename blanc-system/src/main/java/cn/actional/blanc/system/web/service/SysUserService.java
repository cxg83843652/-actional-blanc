package cn.actional.blanc.system.web.service;


import cn.actional.blanc.common.utils.PageUtils;
import cn.actional.blanc.system.web.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-10 21:31:25
 */
public interface SysUserService extends IService<SysUserEntity> {
    PageUtils queryPage(Map<String, Object> params);


}
