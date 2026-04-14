package com.example.boardrental.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    @Before("within(com.example.boardrental.controllers.*)")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        log.info("{} {} -- {}({})",
                request.getMethod(),
                request.getRequestURI(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }
}
