package com.infy.payment.dto;

import java.util.List;

import lombok.Data;

@Data
public class SeatsDTO {

	private Integer movieId;
	private Integer showId;
	private Integer theatreId;
	private List<String> selectedSeats;
}
