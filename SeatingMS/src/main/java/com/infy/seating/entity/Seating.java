package com.infy.seating.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="seats")
public class Seating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seatingId;
	private Integer showId;
	private Integer theatreId;
	private String seatNumber;
	private String seatType;
	@Enumerated(EnumType.STRING)
	private Status status;
}
