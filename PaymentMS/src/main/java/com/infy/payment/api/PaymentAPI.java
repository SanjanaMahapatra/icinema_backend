package com.infy.payment.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.payment.dto.BookingDTO;
import com.infy.payment.dto.CardDTO;
import com.infy.payment.dto.SeatsDTO;
import com.infy.payment.dto.TheatreDTO;
import com.infy.payment.exception.PaymentException;
import com.infy.payment.service.PaymentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/paymentms")
public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@CircuitBreaker(name="paymentservice", fallbackMethod="getPaymentStatusFallback")
	@PostMapping("/payment-process")
	public ResponseEntity<String> getPaymentStatus(@RequestBody CardDTO cardDetails) throws PaymentException {
		String response = paymentService.getPaymentStatus(cardDetails);
		BookingDTO bookingDTO = webClientBuilder.build().get()
				.uri("http://BookingMS/bookingms/all-booking-details/" + cardDetails.getBookingId()).retrieve()
				.bodyToMono(BookingDTO.class).block();
		SeatsDTO seatsDTO = new SeatsDTO();
		seatsDTO.setMovieId(bookingDTO.getMovieId());
		seatsDTO.setShowId(bookingDTO.getShowId());
		seatsDTO.setTheatreId(bookingDTO.getTheatreId());
		seatsDTO.setSelectedSeats(bookingDTO.getSelectedSeats());
		webClientBuilder.build().put().uri("http://SeatingMS/seatingms/update-seats").bodyValue(seatsDTO).retrieve()
				.bodyToMono(Void.class).block();
		bookingDTO.setBookingStatus("Success");
		TheatreDTO theatreDTO = new TheatreDTO();
		theatreDTO.setShowId(bookingDTO.getShowId());
		theatreDTO.setTheatreId(bookingDTO.getTheatreId());
		theatreDTO.setCount(bookingDTO.getSelectedSeats().size());
		webClientBuilder.build().put().uri("http://TheatreMS/theatrems/update-available").bodyValue(theatreDTO).retrieve()
			.bodyToMono(Void.class).block();
		bookingDTO.setBookingStatus("Success");
		webClientBuilder.build().put()
				.uri("http://BookingMS/bookingms/update-status/" + cardDetails.getBookingId()).retrieve()
				.bodyToMono(Void.class).block();
		return new ResponseEntity<>("Payment Success", HttpStatus.CREATED);
	}
	public ResponseEntity<String> getPaymentStatusFallback(@RequestBody CardDTO cardDetails, Throwable throwable){
		
		return new ResponseEntity<>("Wrong attempt! Please try again", HttpStatus.FORBIDDEN);
	}
}
