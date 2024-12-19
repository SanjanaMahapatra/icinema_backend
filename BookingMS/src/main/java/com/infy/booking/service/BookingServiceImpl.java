package com.infy.booking.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.booking.dto.BookingDTO;
import com.infy.booking.dto.BookingReceiptDTO;
import com.infy.booking.dto.MovieDTO;
import com.infy.booking.dto.PriceDetailsDTO;
import com.infy.booking.dto.TheatreDTO;
import com.infy.booking.entity.Booking;
import com.infy.booking.exception.BookingException;
import com.infy.booking.repository.BookingRepository;

@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	WebClient.Builder lbWebClientBuilder;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public BookingReceiptDTO getAllBookingDetails(Long bookingId) throws BookingException{
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		Booking booking = optional.orElseThrow(()->new BookingException("BookingService.BOOKING_NOT_FOUND"));
		
		BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
		
		MovieDTO movie = lbWebClientBuilder.build().get().uri("http://MoviesMS/moviesms/movies/"+bookingDTO.getMovieId()).retrieve().bodyToMono(MovieDTO.class).block();
		TheatreDTO theatre = lbWebClientBuilder.build().get().uri("http://TheatreMS/theatrems/theatre/"+bookingDTO.getTheatreId()).retrieve().bodyToMono(TheatreDTO.class).block();
		
		BookingReceiptDTO bookingReceipt = new BookingReceiptDTO();
		bookingReceipt.setBookingId(bookingId);
		bookingReceipt.setMovieName(movie.getMovieName());
		bookingReceipt.setCensorRating(movie.getCensorRating());
		bookingReceipt.setTheatreName(theatre.getTheatreName());
		bookingReceipt.setShowDate(theatre.getShowDate());
		bookingReceipt.setTheatreId(theatre.getTheatreId());
		bookingReceipt.setMovieId(theatre.getMovieId());
		bookingReceipt.setImageUrl(movie.getImageUrl());
		bookingReceipt.setShowId(booking.getShowId());
		bookingReceipt.setShowTime(bookingDTO.getBookingTime());
		List<String> bookedSeats = bookingDTO.getSelectedSeats();
		List<String> genres = movie.getGenres();
		bookingReceipt.setGenres(genres);
		List<String> languages = movie.getLanguages();
		bookingReceipt.setGenres(languages);
		bookingReceipt.setSelectedSeats(bookedSeats);
		bookingReceipt.setTotalAmount(bookingDTO.getTotalPrice());
		
		return bookingReceipt;
	}
	@Override
	public PriceDetailsDTO getBookingPriceDetails(Long bookingId) throws BookingException{
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		Booking booking = optional.orElseThrow(()->new BookingException("BookingService.BOOKING_NOT_FOUND"));
		
		BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
		Double goldPrice = 250.00;
		Double classicPrice = 150.00;
		Double seatPrice = 0.0;
		for(String seatNumber: bookingDTO.getSelectedSeats()) {
			if(seatNumber.startsWith("I")) {
				seatPrice += goldPrice;
			}else {
				seatPrice += classicPrice;
			}
		}
		Double convenienceFee = seatPrice*0.10;
		Double gst = seatPrice*0.18;
		Double totalPrice = seatPrice+convenienceFee+gst;
		booking.setTotalPrice(totalPrice);
		
		PriceDetailsDTO priceDetails = new PriceDetailsDTO();
		priceDetails.setBookingId(bookingId);
		priceDetails.setSeatPrice(seatPrice);
		priceDetails.setConvienienceFee(convenienceFee);
		priceDetails.setGst(gst);
		priceDetails.setTotalPrice(totalPrice);
		
		return priceDetails;
	}
	
	@Override
	public Long addBookingDetails(BookingDTO bookingDTO) throws BookingException{
		Booking booking = modelMapper.map(bookingDTO, Booking.class);
		bookingRepository.save(booking);
		return booking.getBookingId();
	}
	
	@Override
	public void updateBookingStatus(Long bookingId) throws BookingException {
		Optional<Booking> optional = bookingRepository.findById(bookingId);
		Booking booking = optional.orElseThrow(()->new BookingException("BookingService.BOOKING_NOT_FOUND"));
		booking.setBookingStatus("SUCCESS");
	}
	
}
