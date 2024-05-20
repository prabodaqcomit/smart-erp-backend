package lk.quantacom.smarterpbackend.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Settings {

    static String IMAGE_PATH=System.getProperty("user.home")+"/SMART_ERP/SETTINGS/PROFILE_IMAGES";

    public static String CUSTOMER_IMAGES=IMAGE_PATH+"/CUSTOMER_IMAGES/";

    public static String SUPPLIER_IMAGES=IMAGE_PATH+"/SUPPLIER_IMAGES/";

    public static String ITEM_IMAGES=IMAGE_PATH+"/ITEM_IMAGES/";


    public static String readSettings(String key){

        try {

            File sf=new File("settings.pos");
            if(sf.exists()){

                List<String> lines= FileUtils.readLines(sf,"utf-8");

                for(String oneLine:lines){

                    if(!oneLine.trim().isEmpty()){
                        String[] data=oneLine.trim().split("=");
                        if(key.equalsIgnoreCase(data[0])){
                            return data[1];
                        }
                    }

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

//    public static void main(String[] args) {
//
//        System.out.println(readSettings("COMPANY_ADDRESS_ONE"));
//
//    }

}
