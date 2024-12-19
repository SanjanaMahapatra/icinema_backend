package com.infy.theatre.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.theatre.dto.TheatreDTO;
import com.infy.theatre.exception.TheatreException;


public interface TheatreService {
	public TheatreDTO getTheatre(Integer theatreId) throws TheatreException;
	public List<TheatreDTO> getTheatres(Long movieId, LocalDate showDate) throws TheatreException;
	public String updateSeats(Integer theatreId, Integer showId, Integer count) throws TheatreException ;
}
