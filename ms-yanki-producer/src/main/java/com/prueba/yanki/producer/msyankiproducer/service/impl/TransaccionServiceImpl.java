package com.prueba.yanki.producer.msyankiproducer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.yanki.producer.msyankiproducer.service.TransaccionService;
import com.prueba.yanki.producer.msyankiproducer.util.Constante;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * a.
 * <b>Class</b>: TransaccionServiceImpl <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Febrero 08, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransaccionServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Void> sendTransactionPay() throws IOException {
        JsonNode jsonNode = null;
        try {
            InputStream inputStream = TransaccionServiceImpl.class.getResourceAsStream("/transactionPay.json");
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(inputStream);

            System.out.println(jsonNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNode != null) kafkaTemplate.send(Constante.TOPIC_TRANSACTION_YANKI, jsonNode.toString());
        return null;
    }

    @Override
    public Mono<Void> sendTransactionWallet() throws IOException {
        JsonNode jsonNode = null;
        try {
            InputStream inputStream = TransaccionServiceImpl.class.getResourceAsStream("/transactionWallet.json");
            ObjectMapper mapper = new ObjectMapper();
            jsonNode = mapper.readTree(inputStream);

            System.out.println(jsonNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNode != null) kafkaTemplate.send(Constante.TOPIC_WALLET_YANKI, jsonNode.toString());
        return null;
    }
}
