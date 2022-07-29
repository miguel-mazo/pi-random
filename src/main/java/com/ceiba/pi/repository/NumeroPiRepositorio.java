package com.ceiba.pi.repository;

import com.ceiba.pi.domain.NumeroPiModelo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class NumeroPiRepositorio {

    public BigDecimal obtenerPI(int cantidadDecimales){
        return NumeroPiModelo.calcular(cantidadDecimales).getValor();
    };
}
