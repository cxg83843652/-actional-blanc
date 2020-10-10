package cn.actional.blanc.system.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 5:27 PM
 *     cors配置
 */
public class CorsConstant {
    public static final List<String> CORS_ALLOW_METHODS = Arrays.asList("GET","POST","PUT","DELETE");
    public static final List<String> CORS_ALLOW_DOMAINS = Collections.singletonList("http://127.0.0.1:8081");

}
