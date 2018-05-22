package com.cefothe.bgjudge.utility;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.cefothe.bgjudge.exam.services.ExamServiceBean.DATE_PATTERN;

@UtilityClass
public class DateUtil {

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String toString(Date date){
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }
}
