package com.infy.seating.service;


import com.infy.seating.dto.SeatingRequestDTO;
import com.infy.seating.dto.SeatingResponseDTO;
import com.infy.seating.exception.SeatingException;

public interface SeatingService {
	
	public SeatingResponseDTO getSeatingList(Integer theatreId, Integer showId) throws SeatingException;
	public void updateStatusOfSeatList(SeatingRequestDTO request) throws SeatingException;
}
