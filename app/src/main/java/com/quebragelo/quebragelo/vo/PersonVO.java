package com.quebragelo.quebragelo.vo;

import com.facebook.AccessToken;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Bruno Casali on 21/04/2015.
 */
public class PersonVO implements Serializable {

    private String bio;
    private Date birthday;
    private String email;
    private LocationVO location;
    private String name;
    private StatusVO status;
    private List<TagVO> tags;
    private AccessToken token;

    public AccessToken getToken() {
        return AccessToken.getCurrentAccessToken();
    }

    public void setToken(AccessToken token) {
        this.token = token;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocationVO getLocation() {
        return location;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusVO getStatus() {
        return status;
    }

    public void setStatus(StatusVO status) {
        this.status = status;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }
}
