package com.jamersc.springboot.employee.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Aspect
@Controller
public class DemoLoggingAspect {

    // setup
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // pointcut declarations
    @Pointcut("execution(* com.jamersc.springboot.employee.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.jamersc.springboot.employee.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.jamersc.springboot.employee.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    public void forAppFlow() {}

    // add advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJointPoint) {

        // display method we are calling
        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method " + theMethod);
        // try to run

        // display the arguments to the method
        Object[] args = theJointPoint.getArgs();

        for (Object tempArg : args) {
            myLogger.info("=====> argument: " + tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJointPoint, Object theResult) {

        // display method we are calling
        String theMethod = theJointPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturnong: calling method " + theMethod);
        // try to run

        // display data returned
        myLogger.info("The result: " + theResult);
    }

}
