package cn.actional.phoneix;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
class PhoenixApplicationTests {

    @Test
    void contextLoads() {
        Date date = new Date();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean before = date.before(new Date());
        System.out.println( "1".equals("1") &&   !before);
    }

}
