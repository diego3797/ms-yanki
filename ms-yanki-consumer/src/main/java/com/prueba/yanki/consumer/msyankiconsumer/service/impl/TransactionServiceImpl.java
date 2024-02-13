package com.prueba.yanki.consumer.msyankiconsumer.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.prueba.yanki.consumer.msyankiconsumer.kafka.Data;
import com.prueba.yanki.consumer.msyankiconsumer.kafka.KafkaMessage;
import com.prueba.yanki.consumer.msyankiconsumer.model.Movements;
import com.prueba.yanki.consumer.msyankiconsumer.model.Wallet;
import com.prueba.yanki.consumer.msyankiconsumer.repository.WalletRepository;
import com.prueba.yanki.consumer.msyankiconsumer.service.TransactionService;
import com.prueba.yanki.consumer.msyankiconsumer.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * a.
 * <b>Class</b>: TransactionServiceImpl <br/>
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
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private WalletRepository walletRepo;

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public TransactionServiceImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * a.
     *
     */
    @Override
    public Mono<Void> registerPay(String msg) throws IOException {
        KafkaMessage<Data> dataMsg = Utils.readKafkaMessageFromString(msg, Data.class);
        Data data = dataMsg.getData();

        BigDecimal balanceExist = Objects.requireNonNull(walletRepo.findWalletByCellphone(
                        data.getCellphoneReceive())
                .block()).getBalance();

        Query query = new Query(Criteria.where("cellphone").is(data.getCellphoneReceive()));
        Update update = new Update().push("movements", Movements.builder()
                        .numberOperation(String.valueOf(
                                Utils.generateNumberOperation()))
                        .cellphoneEmisor(data.getCellphoneEmisor())
                        .cellphoneReceiver(data.getCellphoneReceive())
                        .dateOperation(new Date())
                        .amount(data.getAmount())
                        .build())
                .set("balance", balanceExist.add(data.getAmount()));

        mongoTemplate.updateFirst(query, update, Wallet.class).subscribe();

        return null;
    }

    /**
     * a.
     *
     */
    @Override
    public Mono<Void> registerWallet(String msg) throws IOException {
        KafkaMessage<Wallet> dataMsg = Utils.readKafkaMessageFromString(msg, Wallet.class);
        walletRepo.save(Wallet.builder()
                .documentNumber(dataMsg.getData().getDocumentNumber())
                .cellphone(dataMsg.getData().getCellphone())
                .imei(dataMsg.getData().getImei())
                .email(dataMsg.getData().getEmail())
                .associatedDebitCard(dataMsg.getData().getAssociatedDebitCard())
                .dateCreation(new Date())
                .balance(BigDecimal.ZERO)
                .build()).subscribe();

        return null;
    }


}