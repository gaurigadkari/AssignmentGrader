package com.example.android.grader.models;

import com.example.android.grader.Database.GraderDatabase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

/**
 * Created by Gauri Gadkari on 9/23/17.
 */
@Table(database = GraderDatabase.class)
@Parcel
public class Author extends BaseModel {

    @SerializedName("url")
    @Expose
    public String url;
    @Column
    @PrimaryKey
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("time_zone")
    @Expose
    public String timeZone;
    @SerializedName("utc_offset")
    @Expose
    public int utcOffset;
    @SerializedName("locale")
    @Expose
    public String locale;
    @Column
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @Column
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Avatars getAvatars() {
        return avatars;
    }

}
