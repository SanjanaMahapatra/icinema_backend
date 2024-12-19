package com.infy.payment.entity;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    private Integer bookingId;
    private String cardNumber;
    private String username;
    private LocalDate expiryDate;
    private String cvv;
    private String cardHolderName;
    private Integer availableBal;
}
