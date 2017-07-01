package com.cefothe.bgjudge.exam.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by cefothe on 01.07.17.
 */
@AllArgsConstructor
public class ViewTaskModel implements Serializable {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;
}
