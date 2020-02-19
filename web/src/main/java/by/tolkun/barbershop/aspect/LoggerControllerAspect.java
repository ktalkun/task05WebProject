package by.tolkun.barbershop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Aspect
@Component
public class LoggerControllerAspect {

    private static final Logger LOGGER
            = LogManager.getLogger(LoggerControllerAspect.class);

    //    BookController
    @Before("execution(* by.tolkun.barbershop.controller.BookController.makeReservation(..))")
    public void logMakeReservationBookControllerBefore(JoinPoint joinPoint) {
        LOGGER.debug("{}. Creating reservation {}.", joinPoint.getTarget().getClass(),
                joinPoint.getArgs());
    }

    @AfterReturning("execution(* by.tolkun.barbershop.controller.BookController.makeReservation(..))")
    public void logMakeReservationBookControllerAfterReturning(JoinPoint joinPoint) {
        LOGGER.debug("{}. Reservation was saved.", joinPoint.getTarget().getClass());
    }

    //    MainController
    @AfterReturning("execution(* by.tolkun.barbershop.controller.MainController.sendEmail(..))")
    public void logSendEmailMainControllerAfterReturning(JoinPoint joinPoint) {
        LOGGER.info("{}. Email was sent.");
    }

    //    ProfileController
    @Before("execution(* by.tolkun.barbershop.controller.ProfileController.updateUser(..))")
    public void logUpdateUserProfileControllerBefore(JoinPoint joinPoint) {
        Map<String, String> allParams
                = (Map<String, String>) joinPoint.getArgs()[0];
        MultipartFile avatar = (MultipartFile) joinPoint.getArgs()[1];
        LOGGER.debug("{}. Got avatar: {},  name: {}, surname: {}, patronymic: {}, phone: {}, email: {}.",
                joinPoint.getTarget().getClass(), avatar.getOriginalFilename(),
                allParams.get("name"), allParams.get("surname"),
                allParams.get("patronymic"), allParams.get("phone"),
                allParams.get("email"));
    }

    @AfterReturning("execution(* by.tolkun.barbershop.controller.ProfileController.removeReservation(..))")
    public void logRemoveReservationProfileControllerAfterReturning(JoinPoint joinPoint) {
        int reservationId = (int) joinPoint.getArgs()[0];
        LOGGER.info("{}. Reservation with id = {} removed.",
                joinPoint.getTarget().getClass(), reservationId);
    }

    //    SigninController
    @Before("execution(* by.tolkun.barbershop.controller.SigninController.signin(..))")
    public void logSigninSigninControllerBefore(JoinPoint joinPoint) {
        Map<String, String> allParams
                = (Map<String, String>) joinPoint.getArgs()[0];
        LOGGER.debug("{}. Try to register: surname={}, name={}, patronymic={}, email={}, phone={}, login={}.",
                joinPoint.getTarget().getClass(), allParams.get("surname"),
                allParams.get("name"), allParams.get("patronymic"),
                allParams.get("email"), allParams.get("phone"),
                allParams.get("login"));
    }

    @AfterReturning("execution(* by.tolkun.barbershop.controller.SigninController.signin(..))")
    public void logSigninSigninControllerAfterReturning(JoinPoint joinPoint) {
        Map<String, String> allParams
                = (Map<String, String>) joinPoint.getArgs()[0];
        LOGGER.info("{}. User with login \"{}\" was saved.",
                joinPoint.getTarget().getClass(),
                allParams.get("login"));
    }


    @AfterThrowing(
            pointcut = "within(by.tolkun.barbershop.controller.*)",
            throwing = "e")
    public void logMakeReservationBookControllerAfterThrowing(
            JoinPoint joinPoint,
            Exception e) {
        LOGGER.error("{}. Throwing exception: {}.",
                joinPoint.getTarget().getClass(),
                e);
    }
}
