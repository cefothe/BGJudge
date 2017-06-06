package com.cefothe.bgjudge.submissions.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by cefothe on 05.05.17.
 */
@NoArgsConstructor
@ToString
public class SubmissionTO implements Serializable {

    @Getter
    @Setter
    public String code;
}
