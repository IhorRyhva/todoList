package com.todo.todoList.Reprository;

import com.todo.todoList.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
