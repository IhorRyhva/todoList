package com.todo.todoList.Controllers;

import com.todo.todoList.Helper.Authorization;
import com.todo.todoList.Reprository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.todo.todoList.Model.User;

@Controller
public class AuthorizationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @RequestParam String password, @RequestParam String userName){
        User user = Authorization.findUser(userName, userRepository);
        if(user == null){
            return "redirect:/signup";
        }
        if(user.getPassword().equals(password)) {
            TodoListController.id = user.getId();
            return "redirect:/todoList";
        }
        return "fail";
    }

    @GetMapping("/signup")
    public String signupGet(Model model){
        return "Signup";
    }

    @PostMapping("/signup")
    public String signupPost(Model model, @RequestParam String password, @RequestParam String userName, @RequestParam String email){
        User user = Authorization.findUser(userName, userRepository);
        if(user != null){
            return "redirect:/login";
        }
        User newUser = new User();
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setUserName(userName);
        userRepository.save(newUser);
        TodoListController.id = newUser.getId();
        return "redirect:/todoList ";
    }
}
