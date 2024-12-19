package com.infy.seating.dto;

import java.util.List;

import lombok.Data;

@Data
public class SeatingResponseDTO {
	private List<String> bookedSeats;
	private Integer bookedGoldCnt;
	private Integer bookedClassicCnt;
	
}
