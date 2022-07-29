package com.ceiba.pi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String userMessage;
    private int randomGenerate;
    private String internalMessage;
    private String moreInfo;
}
