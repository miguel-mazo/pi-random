package com.ceiba.pi.dto;

import lombok.Getter;

@Getter
public class RespuestaEliminarRegistroRedisDTO {

    private String message;
    private int record;

    public RespuestaEliminarRegistroRedisDTO(String message, int record){
        this.message = message;
        this.record = record;
    }
}
