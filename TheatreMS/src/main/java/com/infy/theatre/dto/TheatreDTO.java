package com.infy.theatre.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TheatreDTO {
	private Integer theatreId;
	private String theatreName;
	private Long movieId;
	private String location;
	private Integer totalSeats;
	private LocalDate showDate;
	private List<ShowsDTO> shows;
}
