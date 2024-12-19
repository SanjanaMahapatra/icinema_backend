package com.infy.seating.dto;

import com.infy.seating.entity.Status;

import lombok.Data;

@Data
public class SeatingDTO {
	private Integer seatingId;
	private Integer showId;
	private Integer theatreId;
	private String seatNumber;
	private String seatType;
	private Status status;
}
