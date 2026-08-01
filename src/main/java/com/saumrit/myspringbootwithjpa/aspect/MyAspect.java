package com.saumrit.myspringbootwithjpa.aspect;

import com.saumrit.myspringbootwithjpa.dto.PostStudentRequestDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    public static Logger logger= LoggerFactory.getLogger(MyAspect.class);

    @Before(value = "execution(* com.saumrit.myspringbootwithjpa.service.MyStudentService.*(..))")
    public void targetingStudentServiceClassAllMethods(JoinPoint joinPoint){
        int i=joinPoint.getArgs().length;
        logger.info("Targeted method is: {} with {} number of arguments",joinPoint.getSignature().getName(),i);
    }

    @Around(value = "execution(* com.saumrit.myspringbootwithjpa.service.MyStudentService.add*(..))")
    public void targetingStudentServiceClassAddMethodsOnly(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        int i=proceedingJoinPoint.getArgs().length;
        logger.info("Around Logic is kicked In .....Targeted method is: {} with {} number of arguments",proceedingJoinPoint.getSignature().getName(),i);
        PostStudentRequestDTO postStudentRequestDTO = (PostStudentRequestDTO)proceedingJoinPoint.getArgs()[0];
        if(postStudentRequestDTO.getAddressDTO().getHouseRegNumber()==65) {
            logger.error("Hunted House Number is Found");
            throw new Exception("Invalid house number");
        }
        else{
            logger.info("house Number validation is success");
            postStudentRequestDTO.getAddressDTO().setCountry("India");
            proceedingJoinPoint.proceed();
            logger.info("Around Logic is kicked In .....Targeted method is: {} with {} number of arguments",proceedingJoinPoint.getSignature().getName(),i);
        }


    }
}
