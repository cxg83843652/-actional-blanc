package cn.actional.blanc.system.auth.Handler;

import cn.actional.blanc.common.utils.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/25/20 8:34 PM
 *  登录认证成功
 */
@Component
public class BlancAuthticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(OBJECT_MAPPER.writeValueAsString(BaseResponse.success()));
        //super.onAuthenticationSuccess(request, response, authentication);
    }
}
