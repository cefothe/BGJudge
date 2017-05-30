package com.cefothe.bgjudge.workers.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by cefothe on 31.05.17.
 */
@Entity
@Table(name = "tests")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test extends BaseEntity {

    @Lob
    @Column(name = "compiler_error")
    @Getter
    private String compilerError;

    @Getter
    @Setter
    private boolean equals;

    @OneToOne
    @Getter
    private TestResults testResults;

    public Test(String compilerError) {
        this.compilerError = compilerError;
    }

    public Test(TestResults testResults) {
        this.testResults = testResults;
    }
}
