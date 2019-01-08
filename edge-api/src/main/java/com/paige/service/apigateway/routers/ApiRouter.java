package com.paige.service.apigateway.routers;

import com.paige.service.apigateway.Filter.HandlerFilter;
import com.paige.service.apigateway.application.ApiServiceConfig;
import com.paige.service.apigateway.handlers.*;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class ApiRouter {

    private static ApiServiceConfig apiServiceConfig;
    private static ErrorHandler errorHandler;
    private static final String API_PATH = "/api";
    private static final String CONTENT_PATH = "/auth";

    public ApiRouter(ApiServiceConfig apiServiceConfig, ErrorHandler errorHandler) {
        this.apiServiceConfig = apiServiceConfig;
        this.errorHandler = errorHandler;
    }

    public static RouterFunction<?> doRoute(ApiHandler handler) {
        return
                nest(path(API_PATH),
                    nest(accept(APPLICATION_JSON),
                        route(GET(CONTENT_PATH), handler::getContentsCard)
                            .filter(new HandlerFilter())
                    ).andOther(route(RequestPredicates.all(), errorHandler::notFound))
                );
    }

    private static RouterFunction<?> bindToHomeHandler(ApiServiceConfig apiServiceConfig, HomeHandler homeHandler) {

        return RouterFunctions
                .route(GET(API_PATH + apiServiceConfig.getHome().getPath()).and(accept(APPLICATION_JSON)), homeHandler::getContent)
                .andRoute(POST("/api/post/**").and(accept(APPLICATION_JSON)), homeHandler::getContent)
                .andRoute(PUT("/api/put/**").and(accept(APPLICATION_JSON)), homeHandler::getContent)
                .andRoute(DELETE("/api/delete/**").and(accept(APPLICATION_JSON)), homeHandler::getContent);
    }

    private static RouterFunction<?> bindToMatchHandler(ApiServiceConfig apiServiceConfig, MatchHandler matchHandler) {

        return RouterFunctions
                .route(GET(API_PATH + apiServiceConfig.getMatch().getPath()).and(accept(APPLICATION_JSON)), matchHandler::getContent)
                .andRoute(POST("/api/post/**").and(accept(APPLICATION_JSON)), matchHandler::getContent)
                .andRoute(PUT("/api/put/**").and(accept(APPLICATION_JSON)), matchHandler::getContent)
                .andRoute(DELETE("/api/delete/**").and(accept(APPLICATION_JSON)), matchHandler::getContent);
    }

    private static RouterFunction<?> bindToRankingHandler(RankingHandler rankingHandler) {

        return RouterFunctions
                .route(GET(API_PATH + apiServiceConfig.getRank().getPath()).and(accept(APPLICATION_JSON)), rankingHandler::getContent)
                .andRoute(POST("/api/post/**").and(accept(APPLICATION_JSON)), rankingHandler::getContent)
                .andRoute(PUT("/api/put/**").and(accept(APPLICATION_JSON)), rankingHandler::getContent)
                .andRoute(DELETE("/api/delete/**").and(accept(APPLICATION_JSON)), rankingHandler::getContent);
    }

    public static RouterFunction<?> bindToHandlerEx(ApiServiceConfig apiServiceConfig, ServiceHandler serviceHandler
            , ErrorHandler errorHandler) {

        return RouterFunctions
                .route(GET(API_PATH + apiServiceConfig.getNews().getPath())
                        .and(accept(APPLICATION_JSON)), serviceHandler.getNewsHandler()::getContent)
                .filter(new HandlerFilter())
                .andOther(bindToMatchHandler(apiServiceConfig, serviceHandler.getMatchHandler()))
                .andOther(bindToHomeHandler(apiServiceConfig, serviceHandler.getHomeHandler()))
                .andOther(bindToMatchHandler(apiServiceConfig, serviceHandler.getMatchHandler()))
                .andOther(route(RequestPredicates.all(), errorHandler::notFound));
    }

}
