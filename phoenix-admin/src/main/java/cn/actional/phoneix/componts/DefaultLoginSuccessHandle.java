package cn.actional.phoneix.componts;

import cn.actional.phoneix.common.utils.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 1:31 PM
 */
@Component
public class DefaultLoginSuccessHandle extends SimpleUrlLogoutSuccessHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(OBJECT_MAPPER.writeValueAsString(BaseResponse.success("退出登录成功")));
        //super.onAuthenticationSuccess(request, response, authentication);
    }


}
