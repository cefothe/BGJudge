package com.cefothe.exam.models.view;

import com.cefothe.exam.entitities.ExamStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cefothe on 05.05.17.
 */
public class ViewExamModel implements Serializable {

    @Getter
    @Setter
    public long id;

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public Timestamp examDate;

    @Getter
    @Setter
    public long examLength;

    @Getter
    @Setter
    public ExamStatus examStatus;
}
