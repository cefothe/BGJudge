package com.cefothe.bgjudge.participants.entities;

import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.common.entities.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 17.06.17.
 */
@Entity
@Table(name = "participants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Participant extends BaseEntity{

    @OneToOne
    @Getter
    private Examens examens;

    @ManyToMany(fetch = FetchType.EAGER)
    @Getter
    private List<User> participants = new ArrayList<>(0);

    public Participant(Examens examens) {
        this.examens = examens;
    }

    public void addParticipant(@NonNull User student){
        this.participants.add(student);
    }
}
