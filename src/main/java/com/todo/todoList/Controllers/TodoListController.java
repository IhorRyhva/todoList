package com.todo.todoList.Controllers;

import com.todo.todoList.Helper.FindMessage;
import com.todo.todoList.Helper.FormatDate;
import com.todo.todoList.Model.Message;
import com.todo.todoList.Reprository.MessageRepository;
import com.todo.todoList.Reprository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/todoList")
public class TodoListController {
    public static long id;

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    private List<Message> messages;

    @GetMapping
    public String site(Model model){
        messages = FindMessage.find(messageRepository, id);
        return "site";
    }

    @GetMapping("/AllNote")
    public String all(Model model){
        List<Message> messagesAll = FindMessage.findNotDoneMessage(messageRepository);
        model.addAttribute("list", messagesAll);
        return "all";
    }

    @GetMapping("/add")
    public String addGet(Model model){
        return "add";
    }
    @PostMapping("/add")
    public String addPost(Model model, @RequestParam String task, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime taskTime){
        Message message = new Message();
        addMessage(task, taskTime, message);
        messageRepository.save(message);
        return "redirect:/todoList";
    }

    @GetMapping("/Upcoming")
    public String upcomingGet(Model model){
        List<Message> list = FindMessage.findTomorrowMessage(messageRepository);
        model.addAttribute("list", list);
        return "upcoming";
    }
    @GetMapping("/todayTask")
        public String today(Model model){
        List<Message> list = FindMessage.findTodayMessage(messageRepository);
        model.addAttribute("todayList", list);
        return "today";
    }
    @GetMapping("/done")
    public String doneGet(Model model){
        List<Message> list = FindMessage.findDone(messageRepository, id);
        model.addAttribute("list", list);
        return "done";
    }

    private void addMessage(String task, LocalDateTime taskTime, Message message) {
        message.setLocalDateTime(taskTime);
        message.setDate(FormatDate.format(message.getLocalDateTime()));
        message.setMessage(task);
        message.setUserId(id);
        message.setEmail(userRepository.findById(id).orElseThrow().getEmail());
    }

}
