package com.altari.spring.ws.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
/**
 * permet de tracer les appels aux repository
 * @Régis LIMARE
 *
 */
public class LogJpaRepositoryAspect {
    /*@Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void RepositoryMethod() {
    }*/

    /*@Around("RepositoryMethod()")
    public Object restRepositoryMethod(ProceedingJoinPoint pjp) throws Throwable {
        for (Class<?> i : pjp.getTarget().getClass().getInterfaces()) {
            if (i.isAnnotationPresent(RepositoryRestResource.class)) {
                Logger logger = LoggerFactory.getLogger(i.getName());
                logger.debug("appel à la méthode : {}({})", pjp.getSignature().getName(), Arrays.toString(pjp.getArgs()));
                break;
            }
        }
        return pjp.proceed();
    }*/
}
