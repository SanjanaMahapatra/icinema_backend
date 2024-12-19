package com.infy.seating.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.seating.dto.SeatingRequestDTO;
import com.infy.seating.dto.SeatingResponseDTO;
import com.infy.seating.exception.SeatingException;
import com.infy.seating.service.SeatingService;

@RestController
@Validated
@RequestMapping(value="/seatingms")
public class SeatingAPI {
	@Autowired
	private SeatingService seatingService;
	
	
	
	@GetMapping("/booked-seats/{theatreId}/{showId}")
	public ResponseEntity<SeatingResponseDTO> getSeatingList(@PathVariable("theatreId")Integer theatreId, @PathVariable("showId")Integer showId) throws SeatingException{
		SeatingResponseDTO seatingList = seatingService.getSeatingList(theatreId, showId);
		
		return new ResponseEntity<>(seatingList,HttpStatus.OK);
	}
	
	@PutMapping("/update-seats")
	public ResponseEntity<String> updateStatusOfSeatList(@RequestBody SeatingRequestDTO request)throws SeatingException{
		seatingService.updateStatusOfSeatList(request);
		
		return new ResponseEntity<>("Successfully booked seats!",HttpStatus.OK);
	}
}
