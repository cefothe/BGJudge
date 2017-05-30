package com.cefothe.bgjudge.workers.repositories;

import com.cefothe.bgjudge.workers.entities.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 31.05.17.
 */
@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
}
