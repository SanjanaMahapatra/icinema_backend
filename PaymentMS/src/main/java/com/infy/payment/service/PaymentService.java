package com.infy.payment.service;

import com.infy.payment.dto.CardDTO;
import com.infy.payment.exception.PaymentException;

public interface PaymentService {
	
	public String getPaymentStatus(CardDTO cradDTO) throws PaymentException;

}
