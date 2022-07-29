package com.ceiba.pi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRedisDTO {
    private String userMessage;
    private String internalMessage;
    private String moreInfo;
}
