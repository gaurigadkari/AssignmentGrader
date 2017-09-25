package com.example.android.grader.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Gauri Gadkari on 9/23/17.
 */
@Parcel
public class Avatars {

    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("large")
    @Expose
    public String large;

    public Avatars() {

    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

}