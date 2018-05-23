package com.cefothe.bgjudge.exam.models.view;

import com.cefothe.bgjudge.submissions.dto.SubmissionResultTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cefothe on 01.07.17.
 */
@AllArgsConstructor
@NoArgsConstructor
public class ViewTaskModel implements Serializable {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private List<SubmissionResultTO> submissionResults;
}
