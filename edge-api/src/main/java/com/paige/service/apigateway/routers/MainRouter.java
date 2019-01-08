package com.paige.service.apigateway.routers;

import com.paige.service.apigateway.application.ApiServiceConfig;
import com.paige.service.apigateway.handlers.*;
import org.springframework.web.reactive.function.server.RouterFunction;

public class MainRouter {

    public static RouterFunction<?> doRoute(final ApiHandler handler, final ErrorHandler errorHandler) {
        return ApiRouter
                .doRoute(handler);
    }

    public static RouterFunction<?> bindToHandler(final ApiServiceConfig apiServiceConfig
            , final ServiceHandler serviceHandler
            , final ErrorHandler errorHandler) {

        return ApiRouter
                .bindToHandlerEx(apiServiceConfig, serviceHandler, errorHandler);
    }



}
