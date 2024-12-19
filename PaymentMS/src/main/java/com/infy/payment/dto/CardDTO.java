package com.infy.payment.dto;



import lombok.Data;

@Data
public class CardDTO {
	private Integer cardId;
    private Integer bookingId;
    private String cardNumber;
    private String username;
    private String expiryDate;
    private String cvv;
    private String cardHolderName;
    private Integer price;
}
