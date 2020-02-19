package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.view.AllowView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("exceptionStackTrace", ex.getMessage());
        return AllowView.EXCEPTION;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlePageNotFound() {
        return AllowView.NOT_FOUND;
    }
}