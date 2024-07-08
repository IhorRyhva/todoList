package com.todo.todoList.Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public static String format(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm");
        String temp = localDateTime.format(dateTimeFormatter);
        return temp;
    }
}
