package com.example.android.grader.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */
@Parcel
public class Assignment {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("due_at")
    @Expose
    public String dueAt;
    //    @SerializedName("turned_in_count")
//    @Expose
//    public Object turnedInCount;
    @SerializedName("message_id")
    @Expose
    public int messageId;
    //    @SerializedName("post_at")
//    @Expose
//    public Object postAt;
//    @SerializedName("grade_percentage")
//    @Expose
//    public Object gradePercentage;
//    @SerializedName("viewable_in_gradebook")
//    @Expose
//    public Object viewableInGradebook;
//    @SerializedName("default_total")
//    @Expose
//    public Object defaultTotal;
    @SerializedName("scheduled_message_id")
    @Expose
    public int scheduledMessageId;
    @SerializedName("timeline_id")
    @Expose
    public String timelineId;
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
//    @SerializedName("attachments")
//    @Expose
//    public Attachments attachments;
//    @SerializedName("recipients")
//    @Expose
//    public Recipients recipients;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

//    public Object getTurnedInCount() {
//        return turnedInCount;
//    }
//
//    public void setTurnedInCount(Object turnedInCount) {
//        this.turnedInCount = turnedInCount;
//    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

//    public Object getPostAt() {
//        return postAt;
//    }
//
//    public void setPostAt(Object postAt) {
//        this.postAt = postAt;
//    }

//    public Object getGradePercentage() {
//        return gradePercentage;
//    }
//
//    public void setGradePercentage(Object gradePercentage) {
//        this.gradePercentage = gradePercentage;
//    }

//    public Object getViewableInGradebook() {
//        return viewableInGradebook;
//    }
//
//    public void setViewableInGradebook(Object viewableInGradebook) {
//        this.viewableInGradebook = viewableInGradebook;
//    }

//    public Object getDefaultTotal() {
//        return defaultTotal;
//    }
//
//    public void setDefaultTotal(Object defaultTotal) {
//        this.defaultTotal = defaultTotal;
//    }

    public int getScheduledMessageId() {
        return scheduledMessageId;
    }

    public void setScheduledMessageId(int scheduledMessageId) {
        this.scheduledMessageId = scheduledMessageId;
    }

    public String getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(String timelineId) {
        this.timelineId = timelineId;
    }

    public boolean isLockAfterDue() {
        return lockAfterDue;
    }

    public void setLockAfterDue(boolean lockAfterDue) {
        this.lockAfterDue = lockAfterDue;
    }

    public boolean isPastDue() {
        return pastDue;
    }

    public void setPastDue(boolean pastDue) {
        this.pastDue = pastDue;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public Attachments getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(Attachments attachments) {
//        this.attachments = attachments;
//    }
//
//    public Recipients getRecipients() {
//        return recipients;
//    }
//
//    public void setRecipients(Recipients recipients) {
//        this.recipients = recipients;
//    }

}