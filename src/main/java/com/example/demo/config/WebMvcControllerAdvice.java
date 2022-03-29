package com.example.demo.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.service.MessageNotFoundException;

@ControllerAdvice
public class WebMvcControllerAdvice {

//    /*
//     * This method changes empty character to null
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//    }
//
//    @ExceptionHandler(EmptyResultDataAccessException.class)
//    public String handleException(EmptyResultDataAccessException e,Model model) {
//        model.addAttribute("message", e);
//        return "error/CustomPage";
//    }
//
//    @ExceptionHandler(InquiryNotFoundException.class)
//    public String handleException(InquiryNotFoundException e,Model model) {
//        model.addAttribute("message", e);
//        return "error/CustomPage";
//    }

    @ExceptionHandler(MessageNotFoundException.class)
    public String handleException(MessageNotFoundException e,Model model) {
        model.addAttribute("message", e);
        return "error/CustomPage";
    }

}
