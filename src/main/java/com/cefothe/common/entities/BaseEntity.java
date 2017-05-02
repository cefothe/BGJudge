package com.cefothe.common.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by cefothe on 02.05.17.
 */
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity  implements Serializable{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
