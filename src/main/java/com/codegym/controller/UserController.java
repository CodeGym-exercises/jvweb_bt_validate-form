package com.codegym.controller;
import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value={"/","home"})
public class UserController {
    @GetMapping
    ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/result")
    ModelAndView validateForm(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        String path = "result";
        if(bindingResult.hasFieldErrors()){
            path="index";
        }
        ModelAndView modelAndView = new ModelAndView(path);
        return modelAndView;
    }
}
