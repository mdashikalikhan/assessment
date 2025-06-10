package com.assessment.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceLogging {

    @Before("execution(* com.assessment.service.PaymentService.*(..))") public void logBefore(JoinPoint joinPoint) {
        System.out.println("Method calls: " +
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
