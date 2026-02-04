package com.seven.security_template.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/")
    public String index() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .toString();

        System.out.println("username = " + username);
        System.out.println("roles = " + roles);

        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
