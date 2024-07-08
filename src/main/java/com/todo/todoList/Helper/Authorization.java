package com.todo.todoList.Helper;

import com.todo.todoList.Model.User;
import com.todo.todoList.Reprository.UserRepository;

public class Authorization {
    public static User findUser(String userName, UserRepository userRepository){
        Iterable<User> iterable = userRepository.findAll();
        for(User user: iterable){
            if(user.getUserName().equals(userName))
                return user;
        }
        return null;
    }
}
