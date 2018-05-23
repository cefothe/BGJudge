package com.cefothe.bgjudge.submissions.repositories;

import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cefothe on 31.05.17.
 */
@Repository
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {

    @Query(name = Submission.FIND_SUBMISSIONS_MAX_SCORE_BY_EXAM)
    List<Submission> findSubmissionForExamMaxScore(@Param("exam") Examens exam);

    List<Submission> findSubmissionByUserTaskExam(@Param("exam") Examens exam, @Param("task") Task task, @Param("user") User user);

    @Query(name = Submission.FIND_SUBMISSIONS_MAX_SCORE_BY_EXAM)
    Page<Submission> findSubmissionForExamMaxScore(@Param("exam") Examens exam, Pageable pageable);

    Page<Submission> findSubmissionByUserTaskExam(@Param("exam") Examens exam, @Param("task") Task task, @Param("user") User user, Pageable pageable);
}
