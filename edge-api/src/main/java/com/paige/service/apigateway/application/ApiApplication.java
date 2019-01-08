package com.paige.service.apigateway.application;

import com.paige.service.apigateway.application.apiconfig.ApiServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ApiApplication implements CommandLineRunner {

    @Autowired
    ApiServiceConfig apiServiceConfig;

    public static void main(String[] args){

        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ApiServiceInfo getinfo = apiServiceConfig.getHome();
        ApiServiceInfo newsinfo = apiServiceConfig.getNews();
        log.info("service url" + getinfo.getBaseurl());
        log.info("service url" + newsinfo.getBaseurl());
    }
}
