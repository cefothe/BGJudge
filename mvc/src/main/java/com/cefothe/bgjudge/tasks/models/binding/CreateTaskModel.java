package com.cefothe.bgjudge.tasks.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by cefothe on 05.05.17.
 */
@AllArgsConstructor
public class CreateTaskModel {

    @Getter
    @Setter
    public String title;

    @Getter
    @Setter
    public String description;

    @Getter
    @Setter
    public String inputOne;

    @Getter
    @Setter
    public String outputOne;

    @Getter
    @Setter
    public String inputTwo;

    @Getter
    @Setter
    public String outputTwo;
}
