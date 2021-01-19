package kf.plt.tas.adminserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "kf.plt.tas.adminserver.mapper")
@ComponentScan({"kf.plt.admin.jwt.core", "kf.plt.tas.adminserver"})
@SpringBootApplication
public class AdminserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminserverApplication.class, args);
    }

}
