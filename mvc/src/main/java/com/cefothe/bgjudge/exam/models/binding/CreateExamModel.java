package com.cefothe.bgjudge.exam.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by cefothe on 04.05.17.
 */
@NoArgsConstructor
public class CreateExamModel implements Serializable {

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

}
