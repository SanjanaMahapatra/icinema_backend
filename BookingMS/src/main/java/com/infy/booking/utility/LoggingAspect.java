package com.infy.booking.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.booking.exception.BookingException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    
    @AfterThrowing(pointcut="execution(* com.infy.booking.service.*Impl.*(..))",throwing="exception")
    public void logServiceException(BookingException exception)
    {
		LOGGER.error(exception.getMessage(),exception);
    }

}
