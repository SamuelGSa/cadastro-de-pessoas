package com.example.crud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    public static String getDataSistemaToString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        Date data = new Date();
        String dataToString = formatter.format(data);

        return dataToString;
    }
}
