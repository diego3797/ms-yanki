package com.prueba.yanki.producer.msyankiproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.prueba.yanki.producer.msyankiproducer")
public class MsYankiProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsYankiProducerApplication.class, args);
	}

}
