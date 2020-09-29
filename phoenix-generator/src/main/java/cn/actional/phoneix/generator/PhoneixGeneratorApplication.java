package cn.actional.phoneix.generator;

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
@MapperScan("cn.actional.phoneix.generator.mapper")
public class PhoneixGeneratorApplication {



    public static void main(String[] args) {
        SpringApplication.run(PhoneixGeneratorApplication.class, args);
    }
}
