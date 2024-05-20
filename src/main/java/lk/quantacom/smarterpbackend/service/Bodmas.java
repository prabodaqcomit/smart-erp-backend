package lk.quantacom.smarterpbackend.service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Bodmas {

    public static String findValueInBraces(String finalStr) {
        try {

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String foo = finalStr.replaceAll("%","/100");

            return (engine.eval(foo)).toString();

        } catch (ScriptException ex) {
            // Logger.getLogger(ttime.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return  null;
        }
    }


}
