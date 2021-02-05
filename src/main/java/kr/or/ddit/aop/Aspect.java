package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@org.aspectj.lang.annotation.Aspect
public class Aspect {
	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
	}
	
	// 특정 메소드가 실행되기 전에 실행 되어야할 공통의 관심사항 (생성)
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("Aspect.beforeMethod");
		
	}
	
	// around : 특정 메소드 실행 전후
	//		메소드 실행전 - 공통 관심사항
	//		메소드의 원래 로직
	//		메소드 실행후 - 공통 관심사항
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메소드 본 로직 실행 전
		long startTime = System.nanoTime();
		
		// 객체에서 클래스 정보를 알아올 수 있다
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		//joinPoint.getArgs() : 실행 인자
		Object ret = joinPoint.proceed(joinPoint.getArgs());		// 원래 만들어 놓았던 메소드 실행
		
		
		// 메소드 본 로직 실행 후 
		long endTime = System.nanoTime();
		
		logger.debug(" className : {} methodName : {} : duration : {}", className, methodName , endTime-startTime);
		
		
		return ret;
	}
}