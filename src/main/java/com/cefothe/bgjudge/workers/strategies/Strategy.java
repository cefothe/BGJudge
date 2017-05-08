package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.workers.ProgramLanguages;

import java.io.File;
import java.io.IOException;

/**
 * Created by cefothe on 08.05.17.
 */
public interface Strategy {
    void execute(ProgramLanguages programLanguages, Submission submission, File file) throws IOException;
}
