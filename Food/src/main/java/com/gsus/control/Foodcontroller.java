package com.gsus.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Foodcontroller {

    @GetMapping("/")
    public String homepg(){
        return "home";
   }
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }


}
