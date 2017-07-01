package com.cefothe.bgjudge.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cefothe on 11.06.17.
 */
public class RegexUtils {

    private static final String CLASS_NAME_PATTERN_VALUE = "public class (\\w+)";
    private static final Pattern CLASS_NAME_PATTERN = Pattern.compile(CLASS_NAME_PATTERN_VALUE);

    public static String findClassName(String code){
        Matcher matcher = CLASS_NAME_PATTERN.matcher(code);
        if(matcher.find()){
           return matcher.group(1);
        }
        throw  new IllegalArgumentException("Incorrect class name");
    }
}
