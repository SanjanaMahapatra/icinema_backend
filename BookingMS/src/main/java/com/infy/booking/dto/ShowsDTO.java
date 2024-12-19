package com.infy.booking.dto;

import java.time.LocalTime;

import lombok.Data;

@Data
public class ShowsDTO {
	private Integer showId;
	private Integer theatreId;
	private LocalTime showTime;
	private Integer availableSeats;
}
