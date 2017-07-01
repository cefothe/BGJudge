package com.cefothe.bgjudge.workers;

import lombok.Setter;

/**
 * Created by cefothe on 08.05.17.
 */
public abstract class Result {

    private static final String EMPTY_STRING="";

    @Setter
    private String errorStream;

    public String getErrorStream() {
        if(EMPTY_STRING.equals(this.errorStream)){
            return  null;
        }
        return this.errorStream;
    }

}
