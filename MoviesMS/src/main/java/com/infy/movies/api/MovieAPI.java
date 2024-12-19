package com.infy.movies.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.movies.dto.MovieDTO;
import com.infy.movies.exception.MoviesException;
import com.infy.movies.service.MovieService;

@RestController
@Validated
@RequestMapping(value="/moviesms")
public class MovieAPI {
	@Autowired
	private MovieService movieService;
	
	@GetMapping(value="/movies")
	public ResponseEntity<List<MovieDTO>> getAllMovies(@RequestParam(name="rating",required=false) Double rating,@RequestParam(name="language",required=false) String language,@RequestParam(name="genre",required=false) List<String> genre, @RequestParam(name="search",required=false)String search) throws MoviesException{

		List<MovieDTO> movies = movieService.getAllMovies(rating,language,genre,search);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/movies/{movieId}")
	public ResponseEntity<MovieDTO> getMovie(@PathVariable Long movieId) throws MoviesException{
		MovieDTO movie = movieService.getMovie(movieId);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/upcoming-movies")
    public ResponseEntity<List<MovieDTO>> getUpcomingMovies(@RequestParam(name="language", required=false) String language, @RequestParam(name="genre", required=false) List<String> genre) throws MoviesException
    {
		LocalDate currentDate = LocalDate.now();
		List<MovieDTO> movies = movieService.getUpcomingMovies(currentDate,language,genre);
		return new ResponseEntity<>(movies,HttpStatus.OK);
    }
	
	@GetMapping(value="/upcoming-latest-movies")
    public ResponseEntity<List<MovieDTO>> getImmediateUpcomingMovies() throws MoviesException
    {
		LocalDate currentDate = LocalDate.now();
		List<MovieDTO> movies = movieService.getImmediateUpcomingMovies(currentDate);
		return new ResponseEntity<>(movies,HttpStatus.OK);
    }
	
	
	@GetMapping(value="/recommended-movies")
    public ResponseEntity<List<MovieDTO>> getTopCurrentlyShowingMovies() throws MoviesException
    {
		LocalDate currentDate = LocalDate.now();
		List<MovieDTO> movies = movieService.getTopCurrentlyShowingMovies(currentDate);
		return new ResponseEntity<>(movies,HttpStatus.OK);
    }
	
	
}
