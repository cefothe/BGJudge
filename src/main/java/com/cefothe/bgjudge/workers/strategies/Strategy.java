package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.workers.ProgramLanguages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by cefothe on 08.05.17.
 */
public interface Strategy {
    Future<Submission> execute(ProgramLanguages programLanguages, Long submissionId, File file) throws IOException;
}
