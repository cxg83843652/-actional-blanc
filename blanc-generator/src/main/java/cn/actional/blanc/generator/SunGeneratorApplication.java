package cn.actional.blanc.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 9:36 PM
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("cn.actional.blanc.generator.mapper")
public class SunGeneratorApplication {



    public static void main(String[] args) {
        SpringApplication.run(SunGeneratorApplication.class, args);
    }
}
