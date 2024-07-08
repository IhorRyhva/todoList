package com.todo.todoList.Reprository;

import com.todo.todoList.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
