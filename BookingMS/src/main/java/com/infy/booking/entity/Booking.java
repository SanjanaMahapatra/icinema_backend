package com.infy.booking.entity;



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

@Entity
@Data
@Table(name="bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookingId;
	private Integer userId;
	private Integer showId;
	private Long movieId;
	private Integer theatreId;
	private Double totalPrice;
	private String bookingStatus;
	private LocalTime bookingTime;
	
	@ElementCollection
	@CollectionTable(name = "bookings_bookedseats",joinColumns = @JoinColumn(name="booking_id"))
	@Column(name="seat_number")
	private List<String> selectedSeats;

}
