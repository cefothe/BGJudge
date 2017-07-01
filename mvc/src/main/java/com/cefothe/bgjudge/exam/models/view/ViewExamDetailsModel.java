package com.cefothe.bgjudge.exam.models.view;

import com.cefothe.bgjudge.exam.entities.ExamStatus;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 05.05.17.
 */
@NoArgsConstructor
public class ViewExamDetailsModel implements Serializable {

    @Getter
    @Setter
    public Long id;

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
    public List<Task> tasks = new ArrayList<>();

    @Getter
    @Setter
    private User createdBy;

    @Getter
    @Setter
    public ExamStatus examStatus;
}
