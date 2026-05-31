package com.banking.customerservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLoggingAspect {
	private static final Logger log =
            LoggerFactory.getLogger(ServiceLoggingAspect.class);
	@Around("execution(* com.banking.customerservice.service..*(..)) || " +
            "execution(* com.banking.customerservice.controller..*(..))")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable {
        String method = jp.getSignature().toShortString();
        log.info("Entering {}", method);
        Object result = jp.proceed();
        log.info("Exiting {} with result={}", method, result);
        return result;
    }

}
