package com.infy.payment.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.payment.dto.CardDTO;
import com.infy.payment.entity.Cards;
import com.infy.payment.exception.PaymentException;
import com.infy.payment.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service(value = "paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public String getPaymentStatus(CardDTO cardDTO) throws PaymentException{
		Optional<Cards> optional = paymentRepository.findByCardNumber(cardDTO.getCardNumber());
		Cards cardDetails = optional.orElseThrow(() -> new PaymentException("PaymentService.CARD_NOT_FOUND"));
		String fullExpiryDate = "20"+cardDTO.getExpiryDate()+"/01";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate expDate = LocalDate.parse(fullExpiryDate, formatter);
		if(!expDate.equals(cardDetails.getExpiryDate())) {
			throw new PaymentException("PaymentService.CARD_EXPIRED");
		}
		if(cardDetails.getAvailableBal() < cardDTO.getPrice()) {
			throw new PaymentException("PaymentService.INSUFFICIENT_BALANCE");
		}
		if(!cardDTO.getCvv().equals(cardDetails.getCvv())) {
			throw new PaymentException("PaymentService.INVALID_CVV");
		}
		cardDetails.setAvailableBal(cardDetails.getAvailableBal()-cardDTO.getPrice());
		return "payment successfull";
	}
	

}
