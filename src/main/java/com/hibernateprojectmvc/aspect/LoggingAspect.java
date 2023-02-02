package com.hibernateprojectmvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.hibernateprojectmvc.Controller.*.*(..))")
    public void controller() {
    }

    @Pointcut("execution(* com.hibernateprojectmvc.DAO.*.*(..))")
    public void dao() {
    }

    @Pointcut("execution(* com.hibernateprojectmvc.service.*.*(..))")
    public void service() {
    }

    @Before("controller() || dao() || service()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().toShortString());
        Object[] obj = joinPoint.getArgs();
        for (Object objs : obj) {
            logger.info(">>>>>>> Arguments: " + objs);
        }

        logger.info("\nTake the log with before advice");
    }

    @AfterReturning(value = "controller() || dao() || service()", returning = "obj")
    public void afterReturningLog(JoinPoint joinPoint, Object obj) {
        logger.info(joinPoint.getSignature().toShortString());
        // display data returned
        logger.info("Returns the data: " + obj);
    }
}
