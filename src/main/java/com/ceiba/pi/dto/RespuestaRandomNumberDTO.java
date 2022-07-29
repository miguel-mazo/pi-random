package com.ceiba.pi.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RespuestaRandomNumberDTO {
    private int random;
    private BigDecimal PICalc;

    public RespuestaRandomNumberDTO(int random, BigDecimal piCalc){
        this.random = random;
        this.PICalc = piCalc;
    }
}
