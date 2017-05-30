package com.cefothe.bgjudge.submissions.repositories;

import com.cefothe.bgjudge.submissions.entities.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 31.05.17.
 */
@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
}
