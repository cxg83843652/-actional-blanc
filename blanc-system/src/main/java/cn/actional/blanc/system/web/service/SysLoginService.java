package cn.actional.blanc.system.web.service;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-11 00:29
 */
public interface SysLoginService {

    /**
     *  登陆方法
     * @param username  用户名
     * @param password  密码
     * @return      token
     */
    String login(String username, String password);
}
