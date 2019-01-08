package com.paige.service.apigateway.application;

import com.paige.service.apigateway.handlers.ErrorHandler;
import com.paige.service.apigateway.handlers.ServiceHandler;
import com.paige.service.apigateway.routers.MainRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
@EnableWebFlux
public class ApplicationConfig {

    @Bean
    ServiceHandler serviceHandler() {

        return new ServiceHandler();
    };

    @Bean
    ErrorHandler errorHandler() {

        return new ErrorHandler();
    }

    @Bean
    RouterFunction<?> mainRouterFunction(final ApiServiceConfig apiServiceConfig
            , final ErrorHandler errorHandler
            , final ServiceHandler serviceHandler) {

        return MainRouter.bindToHandler(apiServiceConfig, serviceHandler, errorHandler);
    }



}
