package com.todo.todoList.Helper;

import java.time.LocalDateTime;

public class CheckDate {
    public static boolean check(LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        if(localDateTime1.getDayOfYear() == localDateTime2.getDayOfYear()){
            if(localDateTime1.getHour() == localDateTime2.getHour()){
                if(localDateTime1.getMinute() == localDateTime2.getMinute())
                    return true;
            }
        }

        return false;
    }
}
