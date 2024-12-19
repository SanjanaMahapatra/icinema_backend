package com.infy.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class BookingReceiptDTO {
	private Long bookingId;
	private String movieName;
	private String theatreName;
	private LocalDate showDate;
	private Long movieId;
	private Integer theatreId;
	private Integer showId;
	private LocalTime showTime;
	private String censorRating;
	private String imageUrl;
	private List<String> languages;
	private List<String> genres;
	private List<String> selectedSeats;
	private Double totalAmount;
}
