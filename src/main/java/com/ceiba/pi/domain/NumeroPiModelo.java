package com.ceiba.pi.domain;

import java.math.BigDecimal;
import java.math.MathContext;

public class NumeroPiModelo {

    private BigDecimal valor;

    private NumeroPiModelo(BigDecimal valor){
        this.valor = valor;
    }

    public static NumeroPiModelo calcular(int cantidadDecimales) {

        cantidadDecimales = cantidadDecimales + 1;
        MathContext mc = new MathContext(cantidadDecimales);
        BigDecimal pi = new BigDecimal(0);
        BigDecimal limite = new BigDecimal(1).movePointLeft(cantidadDecimales);

        boolean fin = false;

        for (int i = 0; !fin; i++) {
            BigDecimal piK = funcionPi(i, mc);

            pi = pi.add(piK);

            if (piK.compareTo(limite) < 0) {
                fin = true;
            }
        }

        return new NumeroPiModelo(pi.round(mc));
    }

    private static BigDecimal funcionPi(int contador, MathContext mc) {

        int contador8 = 8 * contador;

        BigDecimal val1 = new BigDecimal(4);
        val1 = val1.divide(new BigDecimal(contador8 + 1), mc);

        BigDecimal val2 = new BigDecimal(-2);
        val2 = val2.divide(new BigDecimal(contador8 + 4), mc);

        BigDecimal val3 = new BigDecimal(-1);
        val3 = val3.divide(new BigDecimal(contador8 + 5), mc);

        BigDecimal val4 = new BigDecimal(-1);
        val4 = val4.divide(new BigDecimal(contador8 + 6), mc);

        BigDecimal valor = val1;
        valor = valor.add(val2);
        valor = valor.add(val3);
        valor = valor.add(val4);

        BigDecimal multiplicador = new BigDecimal(16);
        multiplicador = multiplicador.pow(contador);

        BigDecimal one = new BigDecimal(1);
        multiplicador = one.divide(multiplicador, mc);
        valor = valor.multiply(multiplicador);

        return valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
