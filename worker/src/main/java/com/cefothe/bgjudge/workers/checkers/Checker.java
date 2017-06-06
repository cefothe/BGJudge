package com.cefothe.bgjudge.workers.checkers;

import com.cefothe.bgjudge.submissions.entities.Submission;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/1/17.
 */

public interface Checker {

    void check(Submission submission);
}
