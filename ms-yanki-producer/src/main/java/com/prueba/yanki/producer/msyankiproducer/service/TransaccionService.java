package com.prueba.yanki.producer.msyankiproducer.service;

import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * .
 * <b>Class</b>: TransactionService <br/>
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
public interface TransaccionService {

    Mono<Void> sendTransactionPay() throws IOException;

    Mono<Void> sendTransactionWallet() throws IOException;
}
