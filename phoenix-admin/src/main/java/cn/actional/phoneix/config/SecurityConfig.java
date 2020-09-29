package cn.actional.phoneix.config;

import cn.actional.phoneix.common.constant.CorsConstant;
import cn.actional.phoneix.componts.DefaultAuthticationFailureHandler;
import cn.actional.phoneix.componts.DefaultAuthticationSuccessHandler;
import cn.actional.phoneix.componts.DefaultLoginSuccessHandle;
import cn.actional.phoneix.componts.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/25/20 8:05 PM
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DefaultAuthticationSuccessHandler defaultAuthticationSuccessHandler;
    private DefaultAuthticationFailureHandler defaultAuthticationFailureHandler;
    private DefaultLoginSuccessHandle defaultLoginSuccessHandle;
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public void setJwtAuthenticationTokenFilter(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Autowired
    public void setDefaultLoginSuccessHandle(DefaultLoginSuccessHandle defaultLoginSuccessHandle) {
        this.defaultLoginSuccessHandle = defaultLoginSuccessHandle;
    }

    @Autowired
    public void setDefaultAuthticationFailureHandler(DefaultAuthticationFailureHandler defaultAuthticationFailureHandler) {
        this.defaultAuthticationFailureHandler = defaultAuthticationFailureHandler;
    }

    @Autowired
    public void setDefaultAuthticationSuccessHandler(DefaultAuthticationSuccessHandler defaultAuthticationSuccessHandler) {
        this.defaultAuthticationSuccessHandler = defaultAuthticationSuccessHandler;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //session 创建策略：无状态，基于 jwt
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //允许访问登录页面
                .antMatchers("/sys/login").permitAll()
                //所有请求必须登录才能访问
                .anyRequest().authenticated()
                .and()
                // 设置登录页面
                .formLogin().loginProcessingUrl("/sys/login").permitAll()
                //登录成功处理方法
                .successHandler(defaultAuthticationSuccessHandler)
                //登录失败处理方法
                .failureHandler(defaultAuthticationFailureHandler).permitAll()
                .and()
                .logout().logoutSuccessHandler(defaultLoginSuccessHandle)
        .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    /**
     *  配置跨域访问
     * @return   UrlBasedCorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource()  {
        CorsConfiguration configuration = new CorsConfiguration();

        // 允许所有的方法
        configuration.setAllowedMethods(CorsConstant.CORS_ALLOW_METHODS);
        //允许哪些域名访问当前资源
        configuration.setAllowedOrigins(CorsConstant.CORS_ALLOW_DOMAINS);
        configuration.setAllowCredentials(true);
        //应用默认设置
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //针对当前域所有资源应用该配置
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }







}
