package com.infy.theatre.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="theatres")
public class Theatre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer theatreId ;
	private String theatreName;
	private Long movieId;
	private String location;
	private Integer totalSeats;
	private LocalDate showDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="theatre_id",unique=true)
	private List<Shows> shows;
}
