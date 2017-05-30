package com.cefothe.bgjudge.workers.repositories;

import com.cefothe.bgjudge.workers.entities.TestResults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 31.05.17.
 */
@Repository
public interface TestResultsRepository extends CrudRepository<TestResults, Long> {
}
