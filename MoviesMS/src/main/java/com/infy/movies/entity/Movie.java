package com.infy.movies.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="movies")
@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;
	@Column(name="name")
	private String movieName;
	
	@ElementCollection
	@CollectionTable(name = "movie_genres",joinColumns = @JoinColumn(name="movie_id"))
	@Column(name="genre")
	private List<String> genres;
	
	@ElementCollection
	@CollectionTable(name = "movie_languages",joinColumns = @JoinColumn(name="movie_id"))
	@Column(name="language")
	private List<String> languages;
	
	private String description;
	private String imageUrl;
	private String censorRating;
	private double rating;
	private double averageRating;
	private LocalDate releaseDate;
	private LocalTime duration;
}
