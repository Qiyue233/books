package com.example.books.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UIDUtil {
    public static String getUID(String prefix,String suffix){
        return getUID(prefix)+suffix;
    }
    public static String getUID(String prefix){
        return prefix+getUID();
    }
    public static String getUID(){
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return df.format(date);
    }
}
