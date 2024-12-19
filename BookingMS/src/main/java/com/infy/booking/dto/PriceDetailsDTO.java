package com.infy.booking.dto;

import lombok.Data;

@Data
public class PriceDetailsDTO {
	
	private Long bookingId;
	private Double seatPrice;
	private Double convienienceFee;
	private Double gst;
	private Double totalPrice;
}
