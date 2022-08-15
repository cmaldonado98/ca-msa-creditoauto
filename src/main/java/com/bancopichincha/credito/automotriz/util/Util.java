package com.bancopichincha.credito.automotriz.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Util {

    public static LocalDateTime getDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }


}
