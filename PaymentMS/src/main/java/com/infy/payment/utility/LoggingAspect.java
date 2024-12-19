package com.infy.payment.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.payment.exception.PaymentException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    
    @AfterThrowing(pointcut="execution(* com.infy.payment.service.*Impl.*(..))",throwing="exception")
    public void logServiceException(PaymentException exception)
    {
		LOGGER.error(exception.getMessage(),exception);
    }

}
