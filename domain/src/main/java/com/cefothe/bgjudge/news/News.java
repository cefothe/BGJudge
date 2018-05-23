package com.cefothe.bgjudge.news;

import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;


/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 7/6/17.
 */
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends BaseEntity {

    @Getter
    private Date createdDate;

    @Getter
    @ManyToOne
    private User createdBy;

    @Getter
    private String title;

    @Getter
    @Lob
    private String content;

}
