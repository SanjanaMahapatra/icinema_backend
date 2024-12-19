package com.infy.booking.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class BookingDTO {
	private Long bookingId;
	private Integer userId;
	private Integer showId;
	private Long movieId;
	private Integer theatreId;
	private Double totalPrice;
	private String bookingStatus;
	private LocalTime bookingTime;
	private List<String> selectedSeats;
	
}
