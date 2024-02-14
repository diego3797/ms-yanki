package com.prueba.yanki.consumer.msyankiconsumer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



/**
 * .
 * Account
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wallet")
@Builder
public class Wallet {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
    private ObjectId id;

    @Valid
    private String documentNumber;

    @Valid
    private String cellphone;

    @Valid
    private String imei;

    @Valid
    private String email;

    @Valid
    private String associatedDebitCard;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    private List<@Valid Movements> movements = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dateCreation;

    @Valid
    private BigDecimal balance;
}
