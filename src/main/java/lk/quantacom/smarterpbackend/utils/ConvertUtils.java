package lk.quantacom.smarterpbackend.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConvertUtils {

    public static String convertDateToStr(Date date) {

        String out = "";

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            out = sdf.format(date);

        } catch (Exception e) {

        }

        return out;
    }

    public static Date convertStrToDate(String date) {

        Date out = null;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            if(date.length()==10){
                out = sdf2.parse(date);
            }else{
                out = sdf.parse(date);
            }


        } catch (Exception e) {

        }

        return out;
    }



}
