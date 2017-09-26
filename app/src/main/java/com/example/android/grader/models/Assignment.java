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
 * Created by Gauri Gadkari on 9/22/17.
 */
@Table(database = GraderDatabase.class)
@Parcel
public class Assignment extends BaseModel {

    @SerializedName("url")
    @Expose
    public String url;
    @Column
    @PrimaryKey(autoincrement = true)
    @SerializedName("id")
    @Expose
    public int id;
    @Column
    @SerializedName("title")
    @Expose
    public String title;
    @Column
    @SerializedName("description")
    @Expose
    public String description;
    @Column
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("due_at")
    @Expose
    @Column
    public String dueAt;
    @SerializedName("message_id")
    @Expose
    public int messageId;
    @SerializedName("scheduled_message_id")
    @Expose
    public int scheduledMessageId;
    @SerializedName("timeline_id")
    @Expose
    public String timelineId;
    @Column
    @SerializedName("lock_after_due")
    @Expose
    public boolean lockAfterDue;
    @SerializedName("past_due")
    @Expose
    public boolean pastDue;
    @SerializedName("reviewed")
    @Expose
    public boolean reviewed;
    @SerializedName("creator")
    @Expose
    public Author author;

    public Assignment() {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public void setLockAfterDue(boolean lockAfterDue) {
        this.lockAfterDue = lockAfterDue;
    }

}