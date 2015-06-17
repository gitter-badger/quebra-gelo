package com.quebragelo.quebragelo.vo;

import android.net.Uri;
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
    private String user_id;
    private String image;
    private String phone;
    private LocationVO location;
    private String name;
    private StatusVO status;
    private String fb_access_token;

    public static PersonVO load(String userID){
        // query the api to search by user with this query: people/find_by?hash[user_id]=925639297460
//        LoadPersonTask task = new LoadPersonTask();
//        task.execute();
//
//        return task.getPersonVO();
        return null;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String userID) {
        this.user_id = userID;
    }

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

    // type could be: square, small, normal, large see Constraint class.
    public Uri getImageLink(String type){
        return Uri.parse("https://graph.facebook.com" + getImage() + "?type=" + type);
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
