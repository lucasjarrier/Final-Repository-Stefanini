package com.orbitallcorp.hack21.cards.domains;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * @author Lucas Jarrier
 * This classe is my Model CARD!
 */

@Entity
@Getter
@Setter
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    private Long cardNumber;

    @NonNull
    @NotNull
    private String embossName;

    @NonNull
    @NotNull
    private String customerName;

    @NonNull
    @NotNull
    private String documentNumber;

    private String motherName;
    private String address;
    private String city;

}
