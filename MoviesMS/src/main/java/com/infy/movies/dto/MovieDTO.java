package com.infy.movies.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class MovieDTO {
	private Long movieId;
	private String movieName;
	private List<String> genres;
	private List<String> languages;
	private String description;
	private String imageUrl;
	private String censorRating;
	private double rating;
	private double averageRating;
	private LocalDate releaseDate;
	private LocalTime duration;
	
}
