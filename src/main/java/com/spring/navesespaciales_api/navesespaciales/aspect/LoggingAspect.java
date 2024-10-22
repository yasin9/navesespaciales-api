package com.spring.navesespaciales_api.navesespaciales.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.spring.navesespaciales_api.navesespaciales.controller.NaveController.getNaveById(..)) && args(id,..)")
    public void logBeforeIfNegativeId(Long id) {
        if (id < 0) {
            logger.warn("Solicitud realizada para una nave con identificación negativa: " + id);
        }
    }

}
