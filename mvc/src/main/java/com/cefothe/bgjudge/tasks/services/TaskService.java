package com.cefothe.bgjudge.tasks.services;

import com.cefothe.bgjudge.tasks.models.binding.CreateTaskModel;

/**
 * Created by cefothe on 05.05.17.
 */
public interface TaskService {
    void createTask(CreateTaskModel createTaskModel, Long examId);
}
