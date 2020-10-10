package cn.actional.blanc.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author actional
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.actional.blanc.admin.mapper"})
public class SunApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunApplication.class, args);
    }

}
