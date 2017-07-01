package com.cefothe.bgjudge.home.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by cefothe on 26.06.17.
 */
@AllArgsConstructor
public class HomePageModel {

    @Getter
    @Setter
    public List<ContestModel> contestModel;
}
