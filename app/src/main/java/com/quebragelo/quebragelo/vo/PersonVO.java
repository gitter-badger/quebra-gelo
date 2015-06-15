package com.quebragelo.quebragelo.vo;

import com.quebragelo.quebragelo.helper.JSONSerializer;
import com.quebragelo.quebragelo.helper.WebAddressable;

import java.util.Date;

/**
 * Created by Bruno Casali on 21/04/2015.
 */
public class PersonVO extends JSONSerializer implements WebAddressable {

    private String bio;
    private Date birthday_at;
    private String email;
    private String image;
    private String phone;
    private LocationVO location;
    private String name;
    private StatusVO status;
    private String fb_access_token;

    public String getFbAccessToken() {
        return fb_access_token;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fb_access_token = fbAccessToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthdayAt() {
        return birthday_at;
    }

    public void setBirthdayAt(Date birthdayAt) {
        this.birthday_at = birthdayAt;
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

    @Override
    public String getPath() {
        return "people/";
    }

    @Override
    public String getSerializerKey() {
        return "person";
    }
}
