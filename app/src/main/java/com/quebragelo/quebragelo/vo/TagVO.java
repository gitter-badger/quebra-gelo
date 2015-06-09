package com.quebragelo.quebragelo.vo;

import java.io.Serializable;

/**
 * Created by Bruno Casali on 21/04/2015.
 */
public class TagVO implements Serializable {

    private String name;
    private String description;
    private PersonVO person;

    public PersonVO getPerson() {
        return person;
    }

    public void setPerson(PersonVO person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
