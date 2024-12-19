package com.infy.theatre.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.theatre.exception.TheatreException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    
    @AfterThrowing(pointcut="execution(* com.infy.theatre.service.*Impl.*(..))",throwing="exception")
    public void logServiceException(TheatreException exception)
    {
		LOGGER.error(exception.getMessage(),exception);
    }

}
