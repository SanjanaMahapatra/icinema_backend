package com.infy.movies.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.movies.exception.MoviesException;

@Component
@Aspect
public class LoggingAspect
{

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
    
    @AfterThrowing(pointcut="execution(* com.infy.movies.service.*Impl.*(..))",throwing="exception")
    public void logServiceException(MoviesException exception)
    {
		LOGGER.error(exception.getMessage(),exception);
    }

}
