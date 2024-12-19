package com.infy.payment.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class BookingDTO {

	private Integer bookingId;
	private Integer showId;
	private Integer theatreId;
	private String bookingStatus;
	private String theatreName;
	private List<String> selectedSeats;
	private LocalDate showDate;
	private LocalTime showTime;
	private Integer movieId;
	private String movieName;
	private List<String> genres;
	private List<String> languages;
	private Double price;
}
