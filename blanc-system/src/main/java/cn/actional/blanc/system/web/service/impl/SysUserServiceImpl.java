package cn.actional.blanc.admin.service;

import cn.actional.blanc.common.utils.PageUtils;
import cn.actional.blanc.system.web.mapper.SysUserMapper;
import cn.actional.blanc.system.web.entity.SysUserEntity;
import cn.actional.blanc.system.web.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-10 21:31:25
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

}
