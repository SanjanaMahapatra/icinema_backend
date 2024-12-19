package com.infy.movies.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.movies.dto.MovieDTO;
import com.infy.movies.entity.Movie;
import com.infy.movies.exception.MoviesException;
import com.infy.movies.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service(value = "movieService")
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	private ModelMapper modelMapper = new ModelMapper();

	// get all the movies data
	@Override
	public List<MovieDTO> getAllMovies(Double rating, String language, List<String> genres,String search) throws MoviesException{
		LocalDate currentDate = LocalDate.now();
		Iterable<Movie> movieList = movieRepository.findByReleaseDateLessThanEqual(currentDate);
		List<MovieDTO> movieDtoList = modelMapper.map(movieList, new TypeToken<List<MovieDTO>>(){}.getType());
		
		if(movieDtoList.isEmpty()) {
			throw new MoviesException("Service.MOVIES_NOT_FOUND");
		}
		return movieDtoList.stream().filter(movie ->
			 (rating == null || movie.getRating()>=rating) && 
					(language==null || movie.getLanguages().contains(language)) &&
					(genres==null || movie.getGenres().stream().anyMatch(genres::contains)) &&
					(search==null || movie.getMovieName().toLowerCase().contains(search.toLowerCase()))
		).toList();
		
		

	}

	// Get the movie based on movie id
	@Override
	public MovieDTO getMovie(Long movieId) throws MoviesException {
		Optional<Movie> optional = movieRepository.findById(movieId);
		Movie movie = optional.orElseThrow(() -> new MoviesException("Service.MOVIE_NOT_FOUND"));

		return modelMapper.map(movie, MovieDTO.class);
	}

	// Filter the results by searching movie names
	@Override
	public MovieDTO getMovieByName(String movieName) throws MoviesException {
		Movie movie = movieRepository.findByMovieName(movieName);
		if (movie == null) {
			throw new MoviesException("Service.MOVIE_NOT_FOUND");
		}

		return modelMapper.map(movie, MovieDTO.class);

	}

	// get the upcoming movies list from the repository, if no movies found then
	// show error message
	@Override
	public List<MovieDTO> getUpcomingMovies(LocalDate currentDate, String language, List<String> genres) throws MoviesException {
		List<Movie> movieList = movieRepository.findByReleaseDateGreaterThan(currentDate);
		if (movieList.isEmpty()) {
			throw new MoviesException("Service.UPCOMING_MOVIES_NOT_FOUND");
		}
		
		List<MovieDTO> movieDtoList =  modelMapper.map(movieList, new TypeToken<List<MovieDTO>>() {
		}.getType());
		
		return movieDtoList.stream().filter(movie ->
		 		(language==null || movie.getLanguages().contains(language)) &&
				(genres==null || movie.getGenres().stream().anyMatch(genres::contains))
	).toList();


	}

	// get the top 10 upcoming movies that are closest to the current date
	@Override
	public List<MovieDTO> getImmediateUpcomingMovies(LocalDate currentDate) throws MoviesException {
		List<Movie> movieList = movieRepository.findByLatestUpcomingMovies(currentDate);
		if (movieList.isEmpty()) {
			throw new MoviesException("Service.IMMEDIATE_UPCOMING_MOVIES_NOT_FOUND");
		}
		List<MovieDTO> movieDtoList = modelMapper.map(movieList, new TypeToken<List<MovieDTO>>() {
		}.getType());

		return movieDtoList.stream().limit(10).toList();

	}

	// get the current movies screened in the theatre
	@Override
	public List<MovieDTO> getCurrentlyShowingMovies(LocalDate currentDate) throws MoviesException {
		List<Movie> movieList = movieRepository.findByReleaseDateLessThanEqual(currentDate);
		if (movieList.isEmpty()) {
			throw new MoviesException("Service.SHOWING_MOVIES_NOT_FOUND");
		}

		return modelMapper.map(movieList, new TypeToken<List<MovieDTO>>() {
		}.getType());
	}

	// get 10 current movies screened in the theatre which have top rating
	@Override
	public List<MovieDTO> getTopCurrentlyShowingMovies(LocalDate currentDate) throws MoviesException {
		List<Movie> movieList = movieRepository.findByTopRatedShowingMovies(currentDate);
		if (movieList.isEmpty()) {
			throw new MoviesException("Service.TOP_RATED_SHOWING_MOVIES_NOT_FOUND");
		}
		List<MovieDTO> movieDtoList = modelMapper.map(movieList, new TypeToken<List<MovieDTO>>() {
		}.getType());

		return movieDtoList.stream().limit(10).toList();
	}
}
