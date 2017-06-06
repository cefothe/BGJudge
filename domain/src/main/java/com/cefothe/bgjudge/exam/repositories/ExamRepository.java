package com.cefothe.bgjudge.exam.repositories;

import com.cefothe.bgjudge.exam.entitities.Examens;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 04.05.17.
 */
@Repository
public interface ExamRepository extends PagingAndSortingRepository<Examens,Long> {
}
