package com.paige.service.apigateway.handlers;

import com.paige.service.apigateway.application.ApiServiceConfig;
import com.paige.service.apigateway.application.apiconfig.ServiceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ServiceHandler {

    @Autowired
    ApiServiceConfig apiServiceConfig;

    @Autowired
    ServiceBuilder serviceBuilder;

    @Autowired
    ErrorHandler errorHandler;

    @Bean
    HomeHandler homeHandler() {

        return new HomeHandler(apiServiceConfig, serviceBuilder, errorHandler);
    }

    @Bean
    NewsHandler newsHandler() {

        return new NewsHandler(apiServiceConfig, serviceBuilder, errorHandler);
    }

    @Bean
    MatchHandler matchHandler() {

        return new MatchHandler(apiServiceConfig, serviceBuilder, errorHandler);
    }

    @Bean
    RankingHandler rankingHandler() {

        return new RankingHandler(apiServiceConfig, serviceBuilder, errorHandler);
    }

    public HomeHandler getHomeHandler() {
        return homeHandler();
    }

    public NewsHandler getNewsHandler() {
        return newsHandler();
    }

    public MatchHandler getMatchHandler() {
        return matchHandler();
    }

    public RankingHandler getRankingHandler() {
        return rankingHandler();
    }


    //public abstract Mono<ServerResponse> getContent(final ServerRequest serverRequest);

}
