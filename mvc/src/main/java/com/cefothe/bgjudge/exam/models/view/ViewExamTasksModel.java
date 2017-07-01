package com.cefothe.bgjudge.exam.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 01.07.17.
 */
@AllArgsConstructor
public class ViewExamTasksModel implements Serializable {

    @Getter
    @Setter
    public Long id;

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    private List<ViewTaskModel> tasks = new ArrayList<>();
}
