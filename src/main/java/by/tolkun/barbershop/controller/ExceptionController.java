package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.view.AllowView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("exceptionStackTrace", ex.getMessage());
        return AllowView.EXCEPTION;
    }
}