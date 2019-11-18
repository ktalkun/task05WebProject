package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.view.AllowView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class NotFoundController {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String showPage(){
        return AllowView.NOT_FOUND;
    }
}