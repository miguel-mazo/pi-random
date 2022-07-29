package com.ceiba.pi.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RespuestaInputNumberDTO {
    private int param;
    private int random;
    private BigDecimal PICalc;

    public RespuestaInputNumberDTO(int param, int random, BigDecimal piCalc){
        this.param = param;
        this.random = random;
        this.PICalc = piCalc;
    }
}
