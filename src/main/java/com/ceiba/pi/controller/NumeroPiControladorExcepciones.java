package com.ceiba.pi.controller;

import com.ceiba.pi.dto.ErrorDTO;
import com.ceiba.pi.dto.ErrorRedisDTO;
import com.ceiba.pi.exception.ErrorDesbordeExcepcion;
import com.ceiba.pi.exception.ErrorNumeroNegativoExcepcion;
import com.ceiba.pi.exception.ErrorRedisExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NumeroPiControladorExcepciones {

    @ExceptionHandler(value = ErrorNumeroNegativoExcepcion.class)
    public ResponseEntity<ErrorDTO> errorNumeroNegativoExcepcionHandler(ErrorNumeroNegativoExcepcion ex){
        ErrorDTO error = new ErrorDTO();
        error.setUserMessage(ex.getUserMessage());
        error.setRandomGenerate(ex.getRandomGenerate());
        error.setInternalMessage(ex.getInternalMessage());
        error.setMoreInfo(ex.getMoreInfo());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ErrorDesbordeExcepcion.class)
    public ResponseEntity<ErrorDTO> errorDesbordeExcepcionHandler(ErrorDesbordeExcepcion ex){
        ErrorDTO error = new ErrorDTO();
        error.setUserMessage(ex.getUserMessage());
        error.setRandomGenerate(ex.getRandomGenerate());
        error.setInternalMessage(ex.getInternalMessage());
        error.setMoreInfo(ex.getMoreInfo());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ErrorRedisExcepcion.class)
    public ResponseEntity<ErrorRedisDTO> errorRedisExcepcionHandler(ErrorRedisExcepcion ex){
        ErrorRedisDTO error = new ErrorRedisDTO();
        error.setUserMessage(ex.getUserMessage());
        error.setInternalMessage(ex.getInternalMessage());
        error.setMoreInfo(ex.getMoreInfo());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
