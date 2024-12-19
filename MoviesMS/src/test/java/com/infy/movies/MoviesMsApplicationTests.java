package com.infy.movies;

import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.movies.dto.MovieDTO;
import com.infy.movies.entity.Movie;
import com.infy.movies.exception.MoviesException;
import com.infy.movies.repository.MovieRepository;
import com.infy.movies.service.MovieService;
import com.infy.movies.service.MovieServiceImpl;

@SpringBootTest(classes = MoviesMsApplicationTests.class)
class MoviesMsApplicationTests {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieService movieService = new MovieServiceImpl();

	@Test
	void getAllMoviesValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		m.setRating(9.8);
		Mockito.when(movieRepository.findAll()).thenReturn(List.of(m));
		List<MovieDTO> movieDtoList = movieService.getAllMovies(9.8, "Hindi", List.of("Action"),"Godfather");
		Assertions.assertEquals(1, movieDtoList.size());

		movieDtoList = movieService.getAllMovies(null, "Hindi", List.of("Action"),"Godfather");
		Assertions.assertEquals(1, movieDtoList.size());
		
		movieDtoList = movieService.getAllMovies(null, "Hindi", List.of("Action"),null);
		Assertions.assertEquals(1, movieDtoList.size());

		Movie m1 = new Movie();
		m1.setMovieId(1L);
		m1.setMovieName("The Godfather");
		m1.setCensorRating("U");
		m1.setAverageRating(9.8);
		m1.setDuration(LocalTime.now());
		m1.setDescription("The aging patriarch of an organized crime");
		m1.setGenres(List.of("Action"));
		m1.setImageUrl("image.png");
		m1.setLanguages(List.of());
		m1.setReleaseDate(LocalDate.now());
		m1.setRating(9.8);

		movieDtoList = movieService.getAllMovies(9.9, null, List.of("Action"),"Godfather");
		Assertions.assertEquals(0, movieDtoList.size());

		
		Movie m2 = new Movie();
		m2.setMovieId(1L);
		m2.setMovieName("The Godfather");
		m2.setCensorRating("U");
		m2.setAverageRating(9.8);
		m2.setDuration(LocalTime.now());
		m2.setDescription("The aging patriarch of an organized crime");
		m2.setGenres(List.of());
		m2.setImageUrl("image.png");
		m2.setLanguages(List.of("hindi"));
		m2.setReleaseDate(LocalDate.now());
		m2.setRating(9.8);
		 
		 movieDtoList = movieService.getAllMovies(9.9, "hindi", null,"Godfather");
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 Movie m3 = new Movie();
		 m3.setMovieId(1L);
		 m3.setMovieName("The Godfather");
		 m3.setCensorRating("U");
		 m3.setAverageRating(9.8);
		 m3.setDuration(LocalTime.now());
		 m3.setDescription("The aging patriarch of an organized crime");
		 m3.setGenres(List.of());
		 m3.setImageUrl("image.png");
		 m3.setLanguages(List.of());
		 m3.setReleaseDate(LocalDate.now());
		 m3.setRating(9.8);
		 
		 movieDtoList = movieService.getAllMovies(null, null, null,null);
		 Assertions.assertEquals(1, movieDtoList.size());
		 
		 Movie m4 = new Movie();
		 m4.setMovieId(1L);
		 m4.setMovieName("The Godfather");
		 m4.setCensorRating("U");
		 m4.setAverageRating(9.8);
		 m4.setDuration(LocalTime.now());
		 m4.setDescription("The aging patriarch of an organized crime");
		 m4.setGenres(List.of("Action"));
		 m4.setImageUrl("image.png");
		 m4.setLanguages(List.of());
		 m4.setReleaseDate(LocalDate.now());
		 m4.setRating(9.8);
		 
		 movieDtoList = movieService.getAllMovies(null, "hindi", List.of("Action"),null);
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 Movie m5 = new Movie();
		 m5.setMovieId(1L);
		 m5.setMovieName("The Godfather");
		 m5.setCensorRating("U");
		 m5.setAverageRating(9.8);
		 m5.setDuration(LocalTime.now());
		 m5.setDescription("The aging patriarch of an organized crime");
		 m5.setGenres(List.of());
		 m5.setImageUrl("image.png");
		 m5.setLanguages(List.of("hindi"));
		 m5.setReleaseDate(LocalDate.now());
		 m5.setRating(9.8);
		 
		 movieDtoList = movieService.getAllMovies(null, "hindi", List.of("Action"),null);
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(null, null, List.of("Action"),null);
		 Assertions.assertEquals(1, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(null, "hindi", null,"Godfather");
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(null, "hindi", null,null);
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(null, null, null,null);
		 Assertions.assertEquals(1, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(9.9, null, List.of(),"The");
		 Assertions.assertEquals(0, movieDtoList.size());

		 movieDtoList = movieService.getAllMovies(9.9, null, List.of("Action", "Crime"),"The");
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(9.9, "hindi", null,null);
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(9.9, "hindi", List.of("crime"),null);
		 Assertions.assertEquals(0, movieDtoList.size());
		 
		 movieDtoList = movieService.getAllMovies(null, null, List.of("action"),"xyz");
		 Assertions.assertEquals(0, movieDtoList.size());
		 
	}

	@Test
	void getAllMoviesInValidTest() {
		List<Movie> m = new ArrayList<>();
		Mockito.when(movieRepository.findAll()).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class,
				() -> movieService.getAllMovies(null, null, null, null));
		Assertions.assertEquals("Service.MOVIES_NOT_FOUND", exception.getMessage());

	}

	@Test
	void getMovieValidTest() throws Exception {
		Long movieId = 1L;
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		Mockito.when(movieRepository.findById(movieId)).thenReturn(Optional.of(m));
		MovieDTO mDTO = movieService.getMovie(1L);
		mDTO.setMovieId(1L);
		Assertions.assertEquals(m.getMovieId(), mDTO.getMovieId());
	}

	@Test
	void getMovieInValidTest() {
		Mockito.when(movieRepository.findById(0L)).thenReturn(Optional.empty());
		Exception exception = Assertions.assertThrows(MoviesException.class, () -> movieService.getMovie(0L));
		Assertions.assertEquals("Service.MOVIE_NOT_FOUND", exception.getMessage());
	}

	@Test
	void getMovieByNameValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		Mockito.when(movieRepository.findByMovieName(anyString())).thenReturn(m);
		MovieDTO mDTO = movieService.getMovieByName("Shawshank");
		mDTO.setMovieId(1L);
		Assertions.assertEquals(m.getMovieId(), mDTO.getMovieId());
	}

	@Test
	void getMovieByNameInValidTest() {
		Movie m = null;
		Mockito.when(movieRepository.findByMovieName(anyString())).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class, () -> movieService.getMovieByName(""));
		Assertions.assertEquals("Service.MOVIE_NOT_FOUND", exception.getMessage());
	}

	@Test
	void getUpcomingMoviesValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		Mockito.when(movieRepository.findByReleaseDateGreaterThan(LocalDate.now())).thenReturn(List.of(m));
		List<MovieDTO> mDTO = movieService.getUpcomingMovies(LocalDate.now(),"English",List.of("Action","Drama"));
		Assertions.assertEquals(1, mDTO.size());
	}

	@Test
	void getUpcomingMoviesInValidTest() {
		List<Movie> m = new ArrayList<>();
		Mockito.when(movieRepository.findByReleaseDateGreaterThan(LocalDate.now())).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class,
				() -> movieService.getUpcomingMovies(null,"English",List.of("Action","Drama")));
		Assertions.assertEquals("Service.UPCOMING_MOVIES_NOT_FOUND", exception.getMessage());

	}

	@Test
	void getImmediateUpcomingMoviesValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		Mockito.when(movieRepository.findByLatestUpcomingMovies(LocalDate.now())).thenReturn(List.of(m));
		List<MovieDTO> mDTO = movieService.getImmediateUpcomingMovies(LocalDate.now());
		Assertions.assertEquals(1, mDTO.size());
	}

	@Test
	void getImmediateUpcomingMoviesInValidTest() {
		List<Movie> m = new ArrayList<>();
		Mockito.when(movieRepository.findByLatestUpcomingMovies(LocalDate.now())).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class,
				() -> movieService.getImmediateUpcomingMovies(null));
		Assertions.assertEquals("Service.IMMEDIATE_UPCOMING_MOVIES_NOT_FOUND", exception.getMessage());

	}

	@Test
	void getCurrentlyShowingMoviesValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		Mockito.when(movieRepository.findByReleaseDateLessThanEqual(LocalDate.now())).thenReturn(List.of(m));
		List<MovieDTO> mDTO = movieService.getCurrentlyShowingMovies(LocalDate.now());
		Assertions.assertEquals(1, mDTO.size());
	}

	@Test
	void getCurrentlyShowingMoviesInValidTest() {
		List<Movie> m = new ArrayList<>();
		Mockito.when(movieRepository.findByReleaseDateLessThanEqual(LocalDate.now())).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class,
				() -> movieService.getCurrentlyShowingMovies(null));
		Assertions.assertEquals("Service.SHOWING_MOVIES_NOT_FOUND", exception.getMessage());

	}

	@Test
	void getTopCurrentlyShowingMoviesValidTest() throws Exception {
		Movie m = new Movie();
		m.setMovieId(1L);
		m.setMovieName("The Godfather");
		m.setCensorRating("U");
		m.setAverageRating(9.8);
		m.setDuration(LocalTime.now());
		m.setDescription("The aging patriarch of an organized crime");
		m.setGenres(List.of("Action"));
		m.setImageUrl("image.png");
		m.setLanguages(List.of("Hindi"));
		m.setReleaseDate(LocalDate.now());
		Mockito.when(movieRepository.findByTopRatedShowingMovies(LocalDate.now())).thenReturn(List.of(m));
		List<MovieDTO> mDTO = movieService.getTopCurrentlyShowingMovies(LocalDate.now());
		Assertions.assertEquals(1, mDTO.size());
	}

	@Test
	void getTopCurrentlyShowingMoviesInValidTest() {
		List<Movie> m = new ArrayList<>();
		Mockito.when(movieRepository.findByTopRatedShowingMovies(LocalDate.now())).thenReturn(m);
		Exception exception = Assertions.assertThrows(MoviesException.class,
				() -> movieService.getTopCurrentlyShowingMovies(null));
		Assertions.assertEquals("Service.TOP_RATED_SHOWING_MOVIES_NOT_FOUND", exception.getMessage());

	}
}
