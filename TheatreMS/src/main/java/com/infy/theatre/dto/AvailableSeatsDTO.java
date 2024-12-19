package com.infy.theatre.dto;



import lombok.Data;

@Data
public class AvailableSeatsDTO {
	private Integer theatreId;
	private Integer showId;
	private Integer count;
}
