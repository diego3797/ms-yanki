package com.prueba.yanki.consumer.msyankiconsumer.service;

import java.io.IOException;
import reactor.core.publisher.Mono;


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
public interface TransactionService {

    Mono<Void> registerPay(String msg) throws IOException;

    Mono<Void> registerWallet(String msg) throws IOException;
}