package com.cefothe.bgjudge.participants.repositories;

import com.cefothe.bgjudge.participants.entities.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cefothe on 18.06.17.
 */
@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long>{
}
