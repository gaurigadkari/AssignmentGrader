package com.example.android.grader.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Gauri Gadkari on 9/23/17.
 */
@Parcel
public class Author {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("type")
    @Expose
    public String type;
    //    @SerializedName("user_title")
//    @Expose
//    public Object userTitle;
    @SerializedName("time_zone")
    @Expose
    public String timeZone;
    @SerializedName("utc_offset")
    @Expose
    public int utcOffset;
    @SerializedName("locale")
    @Expose
    public String locale;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("vanity")
    @Expose
    public String vanity;
    @SerializedName("avatars")
    @Expose
    public Avatars avatars;

    public Author() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Object getUserTitle() {
//        return userTitle;
//    }
//
//    public void setUserTitle(Object userTitle) {
//        this.userTitle = userTitle;
//    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVanity() {
        return vanity;
    }

    public void setVanity(String vanity) {
        this.vanity = vanity;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

}
