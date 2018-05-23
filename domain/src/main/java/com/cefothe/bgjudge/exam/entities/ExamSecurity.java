package com.cefothe.bgjudge.exam.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 26.06.17.
 */
@Entity
@Table(name = "exam_security")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExamSecurity extends BaseEntity {

    @Column(nullable = false, name = "exam_password")
    private String password;

    @ElementCollection
    private List<String> allowedIps = new ArrayList<>();

    /**
     * This method validate if the password that user is apply is the same as {@link Examens} password
     * @param password the password from user
     * @return if the password is correct
     */
    public boolean checkForCorrectPassword(String password){
        return this.password.equals(password);
    }
}
