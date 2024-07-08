package com.todo.todoList.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
//    @GetMapping("/login")
//    public String Login(Model model){
//        return "Login";
//    }
}
