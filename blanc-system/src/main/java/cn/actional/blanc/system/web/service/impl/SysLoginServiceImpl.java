package cn.actional.blanc.system.web.service.impl;

import cn.actional.blanc.system.componts.UserDetailsImpl;
import cn.actional.blanc.system.web.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-11 00:29
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public SysLoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String username, String password) {
        // 该方法会 调用 UserDetailsService的 loadUserByUsername
        /*
            调用关系 AuthenticationManager 的实现类 ->
            ProviderManager 的 authenticate 中 result = provider.authenticate(authentication); ->
            通过循环遍历 AuthenticationProvider 找到支持当前认证方式的 AuthenticationProvider ->
            AuthenticationProvider 的实现类 AbstractUserDetailsAuthenticationProvider 支持当前认证方式 ->
            并且找到 AbstractUserDetailsAuthenticationProvider 的子类 DaoAuthenticationProvider
            之后调用 AbstractUserDetailsAuthenticationProvider 的 authenticate() 方法 ->
            authenticate() 方法会调用子类方法 retrieveUser() 方法 ->
            retrieveUser() 方法 调用 UserDetailsService 的 loadUserByUsername() 方法 ->
            loadUserByUsername() 方法会返回 UserDetails
            通过实现 UserDetailsService 并重写 loadUserByUsername() 方法可以编写查询数据库的业务 ->
            并封装为实现了 UserDetails 接口的自定义类 ->
            返回UserDetails之后被  AbstractUserDetailsAuthenticationProvider 类的 createSuccessAuthentication() 方法
                封装为 UsernamePasswordAuthenticationToken 之后返回 UsernamePasswordAuthenticationToken 同样实现了 Authentication
         */
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // userDetails 被封装在 principal 中
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();



        return null;
    }
}
