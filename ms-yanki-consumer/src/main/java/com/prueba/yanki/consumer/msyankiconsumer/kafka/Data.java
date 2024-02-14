package com.prueba.yanki.consumer.msyankiconsumer.kafka;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * .
 * Data
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Data {
    private String cellphoneEmisor;
    private String cellphoneReceive;
    private BigDecimal amount;
}
