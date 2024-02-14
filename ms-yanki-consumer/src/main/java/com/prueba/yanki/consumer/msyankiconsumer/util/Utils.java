package com.prueba.yanki.consumer.msyankiconsumer.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prueba.yanki.consumer.msyankiconsumer.kafka.KafkaMessage;
import java.io.IOException;
import java.util.Random;

/**
 * .
 * <b>Class</b>: Utils <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Febrero 09, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
public class Utils {

    private Utils() {
        throw new UnsupportedOperationException();
    }

    /**
     * a.
     *
     * @return generateNumber
     */
    public static int generateNumberOperation() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
     * a.
     *
     * @return KafkaMessage
     */
    public static <T> KafkaMessage<T> readKafkaMessageFromString(String jsonString, Class<T> clazz)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = mapper.getTypeFactory().constructParametricType(KafkaMessage.class, clazz);
        KafkaMessage<T> kafkaMessage = mapper.readValue(jsonString, type);
        return kafkaMessage;
    }
}