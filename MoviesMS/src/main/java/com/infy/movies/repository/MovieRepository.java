package com.infy.movies.repository;
import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.movies.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie,Long>{
	
	//Find movie by name
	public Movie findByMovieName(String movieName);
	

	//Get all the upcoming movies list
	@Query("SELECT m FROM Movie m WHERE m.releaseDate>?1")
	public List<Movie> findByReleaseDateGreaterThan(LocalDate currentDate);
	
	//Get all the upcoming movies list based on release date
	@Query("SELECT m FROM Movie m WHERE m.releaseDate>?1 ORDER BY m.releaseDate DESC")
	public List<Movie> findByLatestUpcomingMovies(LocalDate currentDate);
	
	//Get all the movies that have been released already
	public List<Movie> findByReleaseDateLessThanEqual(LocalDate currentDate);
	
	//Get all released movies sorted by highest rating
	@Query("SELECT m FROM Movie m WHERE m.releaseDate<?1 ORDER BY m.rating DESC")
	public List<Movie> findByTopRatedShowingMovies(LocalDate currentDate);
	
	
	

}
