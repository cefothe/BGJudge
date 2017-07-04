package com.cefothe.bgjudge.submissions.dto;

import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by cefothe on 02.07.17.
 */
@AllArgsConstructor
public class SubmissionResultTO {

    @Getter
    @Setter
    private SubmissionStatus submissionStatus;

    @Getter
    @Setter
    private Long submissionId;

    @Getter
    @Setter
    private Integer result;
}
