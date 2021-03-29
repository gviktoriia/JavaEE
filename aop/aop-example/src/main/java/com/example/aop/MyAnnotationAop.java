package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.example.dto.BookDto;
import com.example.service.BookService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MyAnnotationAop {

    @Pointcut("execution(* com.example.*.*.*(..))")
    public void simplePointCut() { }

    @Pointcut(value = "@annotation(myAnnotation)")
    public void simplePointCutWithAnnotation(MyAnnotation myAnnotation) { }

    @SneakyThrows
    @Around("simplePointCut() && simplePointCutWithAnnotation(myAnnotation)")
    public Object around(final ProceedingJoinPoint proceedingJoinPoint, MyAnnotation myAnnotation) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        log.info("in around. before: {}", proceedingJoinPoint.getSignature());

        final Class<?> declaring = methodSignature.getMethod().getDeclaringClass();
        final Object result;
        if (methodSignature.getMethod().getName().equals("getBookById") && declaring.equals(BookService.class) && proceedingJoinPoint.getArgs()[0].equals(1)) {
            result = BookDto.of(1, "my-custom-book");
        } else {
            result = proceedingJoinPoint.proceed();
        }

        log.info("in around. after: {}", proceedingJoinPoint.getSignature());
        return result;
    }

}
