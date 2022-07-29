package com.ceiba.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@SpringBootApplication
public class PiRandomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiRandomApplication.class, args);
	}
}
