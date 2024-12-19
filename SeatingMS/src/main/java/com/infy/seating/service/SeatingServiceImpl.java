package com.infy.seating.service;


import java.util.ArrayList;
import java.util.List;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.seating.dto.SeatingRequestDTO;
import com.infy.seating.dto.SeatingResponseDTO;
import com.infy.seating.entity.Seating;
import com.infy.seating.entity.Status;
import com.infy.seating.exception.SeatingException;
import com.infy.seating.repository.SeatingRepository;

import jakarta.transaction.Transactional;

@Service(value = "seatingService")
@Transactional
public class SeatingServiceImpl implements SeatingService{
	
	@Autowired
	SeatingRepository seatingRepository;
	
//	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public SeatingResponseDTO getSeatingList(Integer theatreId, Integer showId) throws SeatingException{
		List<Seating> seatingList = seatingRepository.getBookedSeatingList(theatreId, showId);
		
		SeatingResponseDTO responseData = new SeatingResponseDTO();
		List<String> totalBookedSeats = new ArrayList<>();
		Integer bookedGoldSeats = 0;
		Integer bookedClassicSeats = 0;
		
		for(Seating seat: seatingList) {
			if(seat.getSeatType().equals("Classic")) {
				bookedClassicSeats++;
			}
			if(seat.getSeatType().equals("Gold")) {
				bookedGoldSeats++;
			}
			totalBookedSeats.add(seat.getSeatNumber());
		}
		responseData.setBookedSeats(totalBookedSeats);
		responseData.setBookedClassicCnt(bookedClassicSeats);
		responseData.setBookedGoldCnt(bookedGoldSeats);
		
		return responseData;
	}
	@Override
	public void updateStatusOfSeatList(SeatingRequestDTO request) throws SeatingException{
				
			List<Seating> seatsList = seatingRepository.findByTheatreIdAndShowId(request.getTheatreId(), request.getShowId());
			
			if(seatsList.isEmpty()) {
				throw new SeatingException("SeatingService.SEAT_NOT_FOUND");
			}
			
			for(Seating seats: seatsList) {
				if(request.getSelectedSeats().contains(seats.getSeatNumber())) {
					seats.setStatus(Status.BOOKED);
				}
			}
//		return seatsList;/
	}
}
