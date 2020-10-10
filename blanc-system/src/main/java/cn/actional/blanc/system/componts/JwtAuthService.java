package cn.actional.blanc.system.componts;

import cn.actional.blanc.system.utils.JwtTokenUtil;
import cn.actional.blanc.system.web.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-10 20:44
 */
@Component
public class JwtAuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


}
