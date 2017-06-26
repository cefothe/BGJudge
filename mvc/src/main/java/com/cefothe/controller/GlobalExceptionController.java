package com.cefothe.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by cefothe on 09.05.17.
 */
@ControllerAdvice
public class GlobalExceptionController {

 //   @ExceptionHandler(Exception.class)
    public String getException(Model model){
        model.addAttribute("view","error/global-error");
        model.addAttribute("title", "Problem with your request");

        return "base-layout";
    }
}
