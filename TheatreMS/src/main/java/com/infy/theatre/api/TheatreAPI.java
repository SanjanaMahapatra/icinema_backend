package com.infy.theatre.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.theatre.dto.AvailableSeatsDTO;
import com.infy.theatre.dto.TheatreDTO;
import com.infy.theatre.exception.TheatreException;
import com.infy.theatre.service.TheatreService;

@RestController
@Validated
@RequestMapping(value="/theatrems")
public class TheatreAPI {

	@Autowired
	private TheatreService theatreService;
	
//	@Autowired
//	private Environment environment;
	
	//get particular theatre details
	@GetMapping(value="/theatre/{theatreId}")
	public ResponseEntity<TheatreDTO> getTheatre(@PathVariable Integer theatreId) throws TheatreException{
		TheatreDTO theatre = theatreService.getTheatre(theatreId);
		return new ResponseEntity<TheatreDTO>(theatre, HttpStatus.OK);
	}
	
	//get theatre details by theatre name
	@GetMapping(value="/theatre")
	public ResponseEntity<List<TheatreDTO>> getTheatreByDate(@RequestParam Long movieId, @RequestParam LocalDate showDate) throws TheatreException{
		List<TheatreDTO> theatre = theatreService.getTheatres(movieId, showDate);
		return new ResponseEntity<>(theatre, HttpStatus.OK);
	}
	
	@PutMapping("/update-available")
	public ResponseEntity<String> updateAvailableSeats(@RequestBody AvailableSeatsDTO request) throws TheatreException{
		String response = theatreService.updateSeats(request.getTheatreId(), request.getShowId(), request.getCount());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
