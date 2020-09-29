package cn.actional.phoneix.componts;

import cn.actional.phoneix.common.exception.ResponseException;
import cn.actional.phoneix.common.utils.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 12:32 AM
 */
@Component
public class DefaultAuthticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String message = exception.getMessage();
        response.getWriter().write(OBJECT_MAPPER.writeValueAsString(
                new ResponseException(BaseResponse.loginError(),message)
        ));

        //super.onAuthenticationFailure(request, response, exception);
    }
}
