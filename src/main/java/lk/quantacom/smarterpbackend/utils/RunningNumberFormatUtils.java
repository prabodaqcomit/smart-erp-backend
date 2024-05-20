package lk.quantacom.smarterpbackend.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.lang.Nullable;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RunningNumberFormatUtils {

    public static String FormatRunningNumber(String CurrentNumber, String Format){

        //Parts are separated from @
        /* Applicable parts
            YYYY, YY                - Current Year 4 digit or 2 digit
            MM                      - Current Month 2 digit
            MMM                     - Current Month name 3 letterers
            MN                      - Current Month name
            DD                      - Current Day of month 2 digit
            SEQ(1,3)                - Running number Sequence starting from 1 and number of zeros to prepend (Eg: 0001)
            |Any Static string|     - Any static string in between two pipe characters (Eg: |ABC|)
        */

        //

        StringBuilder _RunningNumber = new StringBuilder();
        String[] _formatParts = Format.split("@");
        for( int i = 0; i < _formatParts.length; i++ ){
            String _part = _formatParts[i].trim().toUpperCase();
            if(_part.isEmpty()){ continue; }

            DateTimeFormatter _formatter;
            switch (_part){
                case "YYYY":
                    _formatter = DateTimeFormatter.ofPattern("yyyy");
                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
                    break;

                case "YY":
                    _formatter = DateTimeFormatter.ofPattern("yy");
                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
                    break;

                case "MM":
                    _formatter = DateTimeFormatter.ofPattern("MM");
                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
                    break;

                case "MMM":
                    _formatter = DateTimeFormatter.ofPattern("MMM");
                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
                    break;

//                case "MN":
//                    _formatter = DateTimeFormatter.ofPattern("MMM");
//                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
//                    break;

                case "DD":
                    _formatter = DateTimeFormatter.ofPattern("dd");
                    _RunningNumber.append(LocalDateTime.now().format(_formatter));
                    break;

                default:
                    if(_part.contains("SEQ")){
                        Pattern _pattern = Pattern.compile("\\(([0-9]),([0-9])\\)", Pattern.CASE_INSENSITIVE);
                        Matcher _matcher = _pattern.matcher(_part);

                        if(_matcher.find()){
                            int _first = 0;
                            int _paddingLength = 0;

                            MatchResult _matchResult = _matcher.toMatchResult();

//                            String _s1 = _matchResult.group();
//                            String _s2 = _matchResult.group(0);
//                            String _s3 = _matchResult.group(1);
//                            String _s4 = _matchResult.group(2);

                            try{
                                String _f = _matchResult.group(1);
                                _first = Integer.parseInt(_f);
                            }finally {  }
                            _first = (_first == 0) ? 1 : _first;

                            try{
                                String _l = _matchResult.group(2);
                                _paddingLength = Integer.parseInt(_l);
                            }finally { }

                            int _nextNumber = _first;
                            if(CurrentNumber != null){
                                String _runningNumber = CurrentNumber.replaceAll("[^0-9]", "");
                                _nextNumber = Integer.parseInt(_runningNumber) + 1;
                            }

                            //String _nextSeq = String.format(Integer.toString(_nextNumber), "%0" + Integer.toString(_paddingLength)  + "d");
                            String _nextSeq = StringUtils.leftPad(String.valueOf(_nextNumber), _paddingLength, "0");

                            _RunningNumber.append(_nextSeq);
                        }

                    }else{
                        _RunningNumber.append(_part);
                    }
            }
        }



        return _RunningNumber.toString();
    }

}
