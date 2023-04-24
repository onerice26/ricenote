package priv.onerice.ricenote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author onerice
 * @date 2023/3/28
 * @apiNote
 */
@SpringBootApplication
@MapperScan("priv.onerice.ricenote.core.mapper")
public class RiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(RiceAdminApplication.class, args);
    }
}
