package com.ceiba.pi.service;

import com.ceiba.pi.dto.RespuestaEliminarRegistroRedisDTO;
import com.ceiba.pi.dto.RespuestaInputNumberDTO;
import com.ceiba.pi.dto.RespuestaRandomNumberDTO;
import com.ceiba.pi.exception.ErrorDesbordeExcepcion;
import com.ceiba.pi.exception.ErrorNumeroNegativoExcepcion;
import com.ceiba.pi.exception.ErrorRedisExcepcion;
import com.ceiba.pi.repository.NumeroPiRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

@Service
public class NumeroPiServicio {

    @Autowired
    NumeroPiRepositorio numeroPiRepositorio;

    @Autowired
    private RedisTemplate template;

    public RespuestaRandomNumberDTO obtenerPIPorNumeroRandom(int cantidadDecimales){

        try {

            validarSignoNumeroIngresado(cantidadDecimales);
            validarRandomPrecision(cantidadDecimales);

            String respuestaRedis = "";

            if(validarRedis()){
                if(template.opsForValue().get(Integer.toString(cantidadDecimales)) != null) {
                    respuestaRedis = template.opsForValue().get(Integer.toString(cantidadDecimales)) + "";
                }
            }

            if (!respuestaRedis.isEmpty()) {
                return new RespuestaRandomNumberDTO(cantidadDecimales, new BigDecimal(respuestaRedis));
            } else {

                ObjectMapper objectMapper = new ObjectMapper();

                String respuestaRandomNumberJson = objectMapper.writeValueAsString(numeroPiRepositorio.obtenerPI(cantidadDecimales));

                template.opsForValue().set(Integer.toString(cantidadDecimales), respuestaRandomNumberJson, Duration.ofSeconds(60));

                return new RespuestaRandomNumberDTO(cantidadDecimales, new BigDecimal(respuestaRandomNumberJson));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RespuestaInputNumberDTO obtenerPIPorNumeroEntrante(int cantidadDecimales){

        try {

            validarSignoNumeroIngresado(cantidadDecimales);
            validarRandomPrecision(cantidadDecimales);

            BigDecimal num = new BigDecimal(cantidadDecimales);
            num = num.divide(new BigDecimal(2), RoundingMode.UP);

            int min = num.intValue();
            int max = cantidadDecimales + 1;

            int getRandomValue = (int) (Math.random() * (max - min + 1)) + min;

            String respuestaRedis = "";

            if(validarRedis()){
                if(template.opsForValue().get(Integer.toString(getRandomValue)) != null) {
                    respuestaRedis = template.opsForValue().get(Integer.toString(getRandomValue)) + "";
                }
            }

            if (!respuestaRedis.isEmpty()) {
                return new RespuestaInputNumberDTO(cantidadDecimales, getRandomValue, new BigDecimal(respuestaRedis));
            } else {

                ObjectMapper objectMapper = new ObjectMapper();

                String respuestaRandomNumberJson = objectMapper.writeValueAsString(numeroPiRepositorio.obtenerPI(getRandomValue));

                template.opsForValue().set(Integer.toString(getRandomValue), respuestaRandomNumberJson, Duration.ofSeconds(60));

                return new RespuestaInputNumberDTO(cantidadDecimales, getRandomValue, new BigDecimal(respuestaRandomNumberJson));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RespuestaEliminarRegistroRedisDTO eliminarRegistroRedis(int numeroRegistro){

        validarSignoNumeroIngresado(numeroRegistro);

        if(Boolean.TRUE.equals(template.opsForValue().getOperations().delete(String.valueOf(numeroRegistro)))){
            return new RespuestaEliminarRegistroRedisDTO("Record cleared successfully", numeroRegistro);
        } else{
            return new RespuestaEliminarRegistroRedisDTO("Non-existing record in cache", numeroRegistro);
        }
    }

    private void validarSignoNumeroIngresado(int cantidadDecimales){
        if(cantidadDecimales < 0){
            throw new ErrorNumeroNegativoExcepcion("Random parameter is negative", cantidadDecimales, "NEGATIVE_RANDOM_NOT_VALID", "https://httpstatuses.com/400");
        }
    }

    private void validarRandomPrecision(int cantidadDecimales) {
        String maxRandomPrecision = System.getenv().get("MAX_RANDOM_PRECISION");

        int cantidadMaxima = Integer.parseInt(maxRandomPrecision);

        if(cantidadMaxima > 0){
            if (cantidadDecimales > cantidadMaxima) {
                throw new ErrorDesbordeExcepcion("Random parameter would cause overflow", cantidadDecimales, "CONFLICT_RANDOM_NOT_VALID", "https://httpstatuses.com/409");
            }
        }
    }

    private boolean validarRedis(){

        String redisEnabled = System.getenv().get("REDIS_ENABLED");

        if(Integer.parseInt(redisEnabled) == 0){
            throw new ErrorRedisExcepcion("Redis Server Disabled", "CONFLICT_REDIS_DISABLED", "https://httpstatuses.com/409");
        } else {
            return true;
        }
    }
}
