package com.infy.booking.service;

import com.infy.booking.dto.BookingDTO;
import com.infy.booking.dto.BookingReceiptDTO;
import com.infy.booking.dto.MovieDTO;
import com.infy.booking.dto.PriceDetailsDTO;
import com.infy.booking.dto.TheatreDTO;
import com.infy.booking.exception.BookingException;

public interface BookingService {
	
	public Long addBookingDetails(BookingDTO bookingDTO) throws BookingException;
	public BookingReceiptDTO getAllBookingDetails(Long bookingId) throws BookingException;
	public PriceDetailsDTO getBookingPriceDetails(Long bookingId) throws BookingException;
	public void updateBookingStatus(Long bookingId) throws BookingException;
}
