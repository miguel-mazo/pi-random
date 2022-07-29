package com.ceiba.pi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRedisExcepcion extends RuntimeException {

    private String userMessage;
    private String internalMessage;
    private String moreInfo;

    public ErrorRedisExcepcion(String userMessage, String internalMessage, String moreInfo) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.moreInfo = moreInfo;
    }
}
