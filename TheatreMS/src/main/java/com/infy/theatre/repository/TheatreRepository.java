package com.infy.theatre.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.theatre.entity.Theatre;

public interface TheatreRepository extends CrudRepository<Theatre,Integer>{
	
	//find theatre by name
	public Theatre findByTheatreName(String theatreName);
	
	
	public List<Theatre> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);
}
