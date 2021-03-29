package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAop {

    @Pointcut("execution(* com.example.*.*.*(..))")
    public void simplePointCut() { }

    @Before("simplePointCut()")
    public void before(final JoinPoint joinPoint) {
        log.info("Execute before method: {}", joinPoint.getSignature());
    }

    @After("simplePointCut()")
    public void after(final JoinPoint joinPoint) {
        log.info("Execute after method: {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "simplePointCut()", returning = "result")
    public void afterReturning(final JoinPoint joinPoint, final Object result) {
        log.info("After returning result of method: {}. Result: {}", joinPoint.getSignature(), result);
    }

}
