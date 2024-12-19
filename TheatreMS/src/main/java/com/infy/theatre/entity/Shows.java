package com.infy.theatre.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="shows")
public class Shows {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer showId;
	@Column(name="theatre_id")
	private Integer theatreId;
	private LocalTime showTime;
	private Integer availableSeats;
}
