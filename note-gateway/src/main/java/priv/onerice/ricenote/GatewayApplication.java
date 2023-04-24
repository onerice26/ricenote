package priv.onerice.ricenote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author onerice
 * @date 2023/3/8
 * @apiNote
 */

@RestController
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    //在spring cloud gateway中使用RouteLocator的Bean进行路由转发,等价于在yml配置文件中的配置
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String str = "http://localhost:8096/csdn";
        return builder.routes()
                //id 表示被转发到uri地址的id名，
                .route("id", p -> p
                        //predicates，当访问的连接满足http://localhost:8096/csdn时即转发到https://blog.csdn.net
                        .path("/csdn")
                        .uri("https://blog.csdn.net"))
                .build();
    }
}
