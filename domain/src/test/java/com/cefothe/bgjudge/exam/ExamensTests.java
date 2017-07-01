package com.cefothe.bgjudge.exam;

import com.cefothe.bgjudge.exam.entities.ExamStatus;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.user.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by cefothe on 29.06.17.
 */
public class ExamensTests {

    private Examens examens;

    @Before
    public void before(){
        this.examens = new Examens("Test", new Timestamp(new Date().getTime()),120, new User(null,null,null,null), null);
    }

    @Test
    public void testExamAvaivableINProgress(){
        assertThat(examens.checkIfExamAvailable(), equalTo(false));
    }

    @Test
    public void testExamAvaiblePUBLISHED(){
        Date out = Date.from(LocalDateTime.now().minusMinutes(5).atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timestamp = new Timestamp(out.getTime());

        Examens examens = new Examens("Test", timestamp,120, new User(null,null,null,null), null);
        examens.setExamStatus(ExamStatus.PUBLISHED);
        assertThat(examens.checkIfExamAvailable(), equalTo(true));
    }

    @Test
    public void testExamAvaibleWithPast(){
        Date previusValue = java.sql.Date.valueOf(LocalDateTime.now().minusMinutes(120).toLocalDate());
        Timestamp timestamp = new Timestamp(previusValue.getTime());
        Examens examens = new Examens("Test", timestamp,120, new User(null,null,null,null), null);
        assertThat(examens.checkIfExamAvailable(), equalTo(false));
    }

    @Test
    public void testExamAvaibleInFuture(){
        Date previusValue = java.sql.Date.valueOf(LocalDateTime.now().plusMinutes(300).toLocalDate());
        Timestamp timestamp = new Timestamp(previusValue.getTime());
        Examens examens = new Examens("Test", timestamp,120, new User(null,null,null,null), null);
        assertThat(examens.checkIfExamAvailable(), equalTo(false));
    }
}
