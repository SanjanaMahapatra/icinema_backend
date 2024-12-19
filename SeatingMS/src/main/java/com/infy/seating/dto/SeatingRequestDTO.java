package com.infy.seating.dto;

import java.util.List;

import lombok.Data;

@Data
public class SeatingRequestDTO {
	Integer theatreId;
	Integer showId;
	List<String> selectedSeats;
}
