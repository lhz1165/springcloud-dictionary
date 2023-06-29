package com.whq.springtest_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringtestServerApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringtestServerApplication.class, args);
    }


}
