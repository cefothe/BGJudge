package com.cefothe.bgjudge.feedback.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cefothe on 04.07.17.
 */
@Entity
@Table(name = "feedbacks")
public class FeedBack extends BaseEntity {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String content;
}
