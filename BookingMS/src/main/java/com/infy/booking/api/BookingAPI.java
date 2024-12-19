package com.infy.booking.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.booking.dto.BookingDTO;
import com.infy.booking.dto.BookingReceiptDTO;
import com.infy.booking.dto.PriceDetailsDTO;
import com.infy.booking.exception.BookingException;
import com.infy.booking.service.BookingService;

@RestController
@Validated
@RequestMapping(value="/bookingms")
public class BookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	
	@PostMapping("/add-booking")
	public ResponseEntity<Long> addBookingDetails(@RequestBody BookingDTO bookingDTO ) throws BookingException{
		Long bookingId = bookingService.addBookingDetails(bookingDTO);
		
		return new ResponseEntity<>(bookingId, HttpStatus.CREATED);
	}
	@GetMapping("/all-booking-details/{bookingId}")
	public ResponseEntity<BookingReceiptDTO> getAllBookingDetails(@PathVariable("bookingId")Long bookingId) throws BookingException{
			
		BookingReceiptDTO bookingReceipt = bookingService.getAllBookingDetails(bookingId);
		
		return new ResponseEntity<>(bookingReceipt, HttpStatus.OK);
	}
	@GetMapping("/price-details/{bookingId}")
	public ResponseEntity<PriceDetailsDTO> getBookingPriceDetails(@PathVariable("bookingId")Long bookingId) throws BookingException{
		PriceDetailsDTO priceDetails = bookingService.getBookingPriceDetails(bookingId);
		return new ResponseEntity<>(priceDetails, HttpStatus.OK);
	}
	
	@PutMapping("/update-status/{bookingId}")
	public ResponseEntity<String> updateBookingStatus(@PathVariable("bookingId")Long bookingId) throws BookingException{
		bookingService.updateBookingStatus(bookingId);
		return new ResponseEntity<>("Booking Status updated successfully", HttpStatus.OK);
	}

}
