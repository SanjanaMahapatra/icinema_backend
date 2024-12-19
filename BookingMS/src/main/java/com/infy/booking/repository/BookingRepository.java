package com.infy.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.booking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking,Long>{
	
}
