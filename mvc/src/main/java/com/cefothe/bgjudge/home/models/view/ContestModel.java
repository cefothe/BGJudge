package com.cefothe.bgjudge.home.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by cefothe on 26.06.17.
 */
@AllArgsConstructor
public class ContestModel {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private Long examId;
}
