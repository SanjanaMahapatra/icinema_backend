package com.infy.payment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.payment.entity.Cards;

public interface PaymentRepository extends CrudRepository<Cards, Integer> {
	
	Optional<Cards> findByCardNumber(String cardNumber);
	
}
