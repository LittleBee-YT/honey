package com.lbee.honey;

import com.lbee.honey.auth.client.AuthClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lbee.honey.*.mapper")
@AuthClientConfiguration
public class HoneyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoneyAdminApplication.class, args);
    }

}
