package com.infy.movies.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.movies.dto.MovieDTO;
import com.infy.movies.exception.MoviesException;

public interface MovieService {
	
	
	public MovieDTO getMovie(Long movieId) throws MoviesException;
	public List<MovieDTO> getAllMovies(Double rating, String language, List<String> genre, String search ) throws MoviesException;
	public MovieDTO getMovieByName(String movieName) throws MoviesException;
	public List<MovieDTO> getUpcomingMovies(LocalDate currentDate, String language, List<String> genres) throws MoviesException;
	public List<MovieDTO> getImmediateUpcomingMovies(LocalDate currentDate) throws MoviesException;
	public List<MovieDTO> getCurrentlyShowingMovies(LocalDate currentDate) throws MoviesException;
	public List<MovieDTO> getTopCurrentlyShowingMovies(LocalDate currentDate) throws MoviesException;
	
}
