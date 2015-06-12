package com.quebragelo.quebragelo.vo;

import java.io.Serializable;

/**
 * Created by Bruno Casali on 21/04/2015.
 */
public class LocationVO implements Serializable {

    private Long longitude;
    private Long latitude;
    private PersonVO person;

    public PersonVO getPerson() {
        return person;
    }

    public void setPerson(PersonVO person) {
        this.person = person;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
}
