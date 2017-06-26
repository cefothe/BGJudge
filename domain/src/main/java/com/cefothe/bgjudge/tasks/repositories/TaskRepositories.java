package com.cefothe.bgjudge.tasks.repositories;

import com.cefothe.bgjudge.tasks.entities.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 05.05.17.
 */
@Repository
public interface TaskRepositories extends PagingAndSortingRepository<Task,Long> {
}
