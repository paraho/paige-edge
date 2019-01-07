package com.paige.service.apigateway.application.apiconfig;

import lombok.Data;

@Data
public class CommonProperty {

    private long id;
    private String version;
    private String uri;
    private String param;

}