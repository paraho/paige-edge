package com.paige.service.apigateway.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PlayerHandler {
    public <T extends ServerResponse> Mono<T> getName(ServerRequest serverRequest) {

        return null;
    }
}
