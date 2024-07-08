package com.todo.todoList.Controllers;

import com.todo.todoList.Helper.FindMessage;
import com.todo.todoList.Helper.FormatDate;
import com.todo.todoList.Model.Message;
import com.todo.todoList.Reprository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/todoList/AllNote/")
public class DeleteEditMessageController {
    @Autowired
    MessageRepository messageRepository;

//    @GetMapping("{id}")
//    public String main(Model model, @PathVariable(value = "id") long id){
//        model.addAttribute("message", messageRepository.findById(id).orElseThrow());
//        return "EditDelete";
//    }

    @PostMapping("{id}/remove")
    public String remove(Model model, @PathVariable(value = "id") long id){
        Message message = messageRepository.findById(id).orElseThrow();
        FindMessage.DeletedId = id;
        messageRepository.delete(message);
        return "redirect:/todoList/AllNote";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable(value = "id") long id){
        FindMessage.EditId = id;
        Message message = messageRepository.findById(id).orElseThrow();
        model.addAttribute("Message", message);
        return "Edit";
    }
    @PostMapping("{id}/edit")
    public String editPost(Model model, @PathVariable(value = "id") long id, @RequestParam String task, @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)LocalDateTime taskTime){
        Message message = messageRepository.findById(id).orElseThrow();
        message.setMessage(task);
        message.setLocalDateTime(taskTime);
        message.setDate(FormatDate.format(message.getLocalDateTime()));
        messageRepository.save(message);
        return "redirect:/todoList/AllNote";
    }
}
