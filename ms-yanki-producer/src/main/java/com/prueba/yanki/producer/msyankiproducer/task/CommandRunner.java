package com.prueba.yanki.producer.msyankiproducer.task;

import com.prueba.yanki.producer.msyankiproducer.service.TransaccionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    private final TransaccionService txService;

    public CommandRunner(TransaccionService txService) {
        this.txService = txService;
    }

    @Override
    public void run(String... args) throws Exception {
        txService.sendTransactionPay();
    }
}
