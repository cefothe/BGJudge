package com.cefothe.bgjudge.workers.strategies;

import com.cefothe.bgjudge.submissions.entities.Submission;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by cefothe on 08.05.17.
 */
public interface Strategy {
    @Async
    Future<Submission> execute(Long submissionId) throws IOException;
}
