package com.cefothe.bgjudge.workers.calculation;

import com.cefothe.bgjudge.submissions.entities.Submission;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 6/2/17.
 */
public interface ScoreCalculation {
    void calculation(Submission submission);
}
