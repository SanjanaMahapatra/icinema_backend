package com.infy.theatre.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.theatre.dto.ShowsDTO;
import com.infy.theatre.dto.TheatreDTO;
import com.infy.theatre.entity.Shows;
import com.infy.theatre.entity.Theatre;
import com.infy.theatre.exception.TheatreException;
import com.infy.theatre.repository.TheatreRepository;

import jakarta.transaction.Transactional;

@Service(value="theatreService")
@Transactional
public class TheatreServiceImpl implements TheatreService{
	
	@Autowired
	TheatreRepository theatreRepository;
	
	
	private ModelMapper modelMapper = new ModelMapper();

	
	@Override
	public TheatreDTO getTheatre(Integer theatreId) throws TheatreException{
		Optional<Theatre> optional = theatreRepository.findById(theatreId);
		Theatre theatre = optional.orElseThrow(()->new TheatreException("TheatreService.THEATRE_NOT_FOUND"));
		
		return modelMapper.map(theatre, TheatreDTO.class);
	}
	
	//get theatres for movie showing on date
	@Override
	public List<TheatreDTO> getTheatres(Long movieId, LocalDate showDate) throws TheatreException{
		List<Theatre> theatreList = theatreRepository.findByMovieIdAndShowDate(movieId, showDate);
		if(theatreList.isEmpty()) {
			throw new TheatreException("TheatreService.THEATRES_NOT_FOUND");
		}
		
		List<TheatreDTO> result = new ArrayList<>();
		for(Theatre theatre: theatreList) {
			TheatreDTO theatreDTO = new TheatreDTO();
			theatreDTO.setTheatreId(theatre.getTheatreId());
			theatreDTO.setTheatreName(theatre.getTheatreName());
			theatreDTO.setMovieId(theatre.getMovieId());
			theatreDTO.setLocation(theatre.getLocation());
			theatreDTO.setTotalSeats(theatre.getTotalSeats());
			theatreDTO.setShowDate(theatre.getShowDate());
			
			List<ShowsDTO> showsList = new ArrayList<>();
			for(Shows shows: theatre.getShows()) {
				ShowsDTO showDTO = new ShowsDTO();
				showDTO.setShowId(shows.getShowId());
				showDTO.setTheatreId(shows.getTheatreId());
				showDTO.setShowTime(shows.getShowTime());
				showDTO.setAvailableSeats(shows.getAvailableSeats());
				showsList.add(showDTO);
			}
			theatreDTO.setShows(showsList);
			
			result.add(theatreDTO);
		}
		return result;
	}

	@Override
	public String updateSeats(Integer theatreId, Integer showId, Integer count) throws TheatreException {
		Optional<Theatre> optional = theatreRepository.findById(theatreId);
		Theatre theatre = optional.orElseThrow(()->new TheatreException("TheatreService.THEATRE_NOT_FOUND"));
		for(Shows shows: theatre.getShows()) {
			if(shows.getShowId() == showId) {
				shows.setAvailableSeats(shows.getAvailableSeats()-count);
			}
		}
		return "Available Seats Update Success";
	}
	
	
}
