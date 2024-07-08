package com.todo.todoList.Helper;

import com.todo.todoList.Model.Message;
import com.todo.todoList.Reprository.MessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FindMessage {
    private static List<Message> ListMessages;
    public static long DeletedId;
    public static long EditId;

    public static List<Message> find(MessageRepository messageRepository, long userId){
        Iterable<Message> iterable = messageRepository.findAll();
        List<Message> messages = new ArrayList<>();

        for(Message message: iterable){
            if(message.getUserId() == userId){
                messages.add(message);
            }
        }
        ListMessages = messages;
        return messages;
    }
    public static List<Message> findNotDoneMessage(MessageRepository messageRepository){
        delete();
        if(EditId != 0){
            edit(messageRepository);
        }
        List<Message> messages = new ArrayList<>();
        for(Message message: ListMessages){
            if(!message.isSent()){
                messages.add(message);
            }
        }
        return messages;
    }

    private static void delete() {
        for(Message message: ListMessages){
            if(message.getId() == DeletedId){
                ListMessages.remove(message);
                break;
            }
        }
    }
    private static void edit(MessageRepository messageRepository) {
        for(Message message: ListMessages){
            if(message.getId() == EditId){
                ListMessages.remove(message);
                ListMessages.add(messageRepository.findById(EditId).orElseThrow());
                EditId = 0;
                break;
            }
        }
    }

    public static List<Message> findTomorrowMessage(MessageRepository messageRepository){
        delete();
        if(EditId != 0){
            edit(messageRepository);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Message> tomorrow = new ArrayList<>();
        for(Message message: ListMessages){
            if(message.getLocalDateTime().getDayOfYear() == (localDateTime.getDayOfYear() + 1)){
                tomorrow.add(message);
            }
        }
        return tomorrow;
    }

    public static List<Message> findTodayMessage(MessageRepository messageRepository){
        delete();
        if(EditId != 0){
            edit(messageRepository);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Message> today = new ArrayList<>();
        for(Message message: ListMessages){
            if(message.getLocalDateTime().getDayOfYear() == localDateTime.getDayOfYear() ){
                today.add(message);
            }
        }
        return today;
    }
    public static List<Message> findDone(MessageRepository messageRepository, long userId){
        List<Message> messages = new ArrayList<>();
        List<Message> list = messageRepository.findAll();

        for(Message message: list){
            if(message.isSent() && message.getUserId() == userId){
                messages.add(message);
            }
        }
        return messages;
    }
}
