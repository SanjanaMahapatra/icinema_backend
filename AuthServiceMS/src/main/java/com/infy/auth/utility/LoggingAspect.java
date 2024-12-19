package com.infy.auth.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.auth.exception.AuthenticationException;

@Component
@Aspect
public class LoggingAspect {

	public static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "excecution(* com.infy.auth.service.*Impl.*(..)", throwing = "exception")
	public void logServiceException(AuthenticationException exception) {
		LOGGER.error(exception.getMessage());
	}
}
	