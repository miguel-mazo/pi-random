package com.ceiba.pi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDesbordeExcepcion extends RuntimeException{
    private String userMessage;
    private int randomGenerate;
    private String internalMessage;
    private String moreInfo;

    public ErrorDesbordeExcepcion(String userMessage, int randomGenerate, String internalMessage, String moreInfo) {
        this.userMessage = userMessage;
        this.randomGenerate = randomGenerate;
        this.internalMessage = internalMessage;
        this.moreInfo = moreInfo;
    }
}
