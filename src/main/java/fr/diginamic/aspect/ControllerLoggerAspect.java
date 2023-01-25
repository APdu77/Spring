package fr.diginamic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// cible toutes les methodes commencant par "get" dans le package toutes classes
	// confondues
	@After("execution(* fr.diginamic.controller..get*(..)))")
	public void startsWithGet(JoinPoint joinPoint) {
		logger.info("Executing controller method {}", joinPoint.getSignature().getName());
	}

	@After("execution(public * fr.diginamic.controller..*(..)))")
	public void anyPlublicMethod(JoinPoint joinPoint) {
		logger.info("Entering controller method {}", joinPoint.getSignature().getName());
	}

	@AfterThrowing("execution(public * fr.diginamic.service.PersonService.update(..)))")
	public void saveException(JoinPoint joinPoint) {
		logger.info("-----Threw the exception {}", joinPoint.getSignature().getName());
	}

	@Around("within(fr.diginamic.service..*)")
	public Object chronometer(ProceedingJoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		try {
			Object objectValue = joinPoint.proceed();
			long end = System.currentTimeMillis();
			System.out.println(end - start +"ms");
			logger.info("Executing controller method {}", joinPoint.getSignature());
			return objectValue;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
