package com.todo.todoList;

import com.todo.todoList.Helper.CheckDate;
import com.todo.todoList.Model.Message;
import com.todo.todoList.Reprository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Schedule {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private EmailSenderService senderService;


    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        schedule();
    }

    @Scheduled(fixedRate = 60000)
    public void schedule(){
        Iterable<Message> messages = messageRepository.findAll();
        LocalDateTime localDateTime = LocalDateTime.now();
        for (Message message: messages){
            if(CheckDate.check(localDateTime, message.getLocalDateTime())){
                sendMail(message);
                message.setSent(true);
                System.out.println(message.getMessage() + message.getUserId() + " " + message.isSent());
                messageRepository.save(message);
            }
        }
    }

    public void sendMail(Message message){
        senderService.sendMail(message.getEmail(), "TODO", message.getMessage());
    }

}
