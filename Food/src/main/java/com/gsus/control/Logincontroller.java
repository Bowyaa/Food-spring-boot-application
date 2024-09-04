package com.gsus.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Logincontroller {
@GetMapping("/login")
public String login()
{
    return "login";
}

    @GetMapping("/access-denied")
    public String acessDenied()
    {
        return "access-denied";
    }
}
