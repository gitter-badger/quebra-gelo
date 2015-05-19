package com.quebragelo.quebragelo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bruno Casali on 21/04/2015.
 */
public class StatusVO implements Serializable {

    private PersonVO person;
    private Date last;
    private Date lastLogged;

    public PersonVO getPerson() {
        return person;
    }

    public void setPerson(PersonVO person) {
        this.person = person;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    public Date getLastLogged() {
        return lastLogged;
    }

    public void setLastLogged(Date lastLogged) {
        this.lastLogged = lastLogged;
    }
}
