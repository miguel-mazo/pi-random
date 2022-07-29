package com.ceiba.pi.controller;

import com.ceiba.pi.dto.RespuestaEliminarRegistroRedisDTO;
import com.ceiba.pi.dto.RespuestaInputNumberDTO;
import com.ceiba.pi.dto.RespuestaRandomNumberDTO;
import com.ceiba.pi.service.NumeroPiServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class NumeroPiControlador {

    @Autowired
    NumeroPiServicio numeroPiServicio;

    @GetMapping("/getpi")
    public RespuestaRandomNumberDTO obtenerPiRandom(@RequestParam("random_number") int randomNumber){
        return this.numeroPiServicio.obtenerPIPorNumeroRandom(randomNumber);
    }

    @GetMapping("/getpi_random")
    public RespuestaInputNumberDTO obtenerPiInput(@RequestParam("input_number") int inputNumber){
        return this.numeroPiServicio.obtenerPIPorNumeroEntrante(inputNumber);
    }

    @DeleteMapping("/delete_record")
    public RespuestaEliminarRegistroRedisDTO eliminarRegistro(@RequestParam("random_number") int randomNumber){
        return this.numeroPiServicio.eliminarRegistroRedis(randomNumber);
    }
}
