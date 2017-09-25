package com.example.android.grader.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Gauri Gadkari on 9/23/17.
 */
@Parcel
public class Submission {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("submitted_at")
    @Expose
    public String submittedAt;
    @SerializedName("assignment_id")
    @Expose
    public int assignmentId;
    @SerializedName("content")
    @Expose
    public String content;
    //    @SerializedName("num_replies")
//    @Expose
//    public Object numReplies;
    @SerializedName("status")
    @Expose
    public String status;
    //    @SerializedName("attachments")
//    @Expose
//    public Attachments attachments;
    @SerializedName("creator")
    @Expose
    public Author author;

    public Submission() {

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

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Object getNumReplies() {
//        return numReplies;
//    }
//
//    public void setNumReplies(Object numReplies) {
//        this.numReplies = numReplies;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    public Attachments getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(Attachments attachments) {
//        this.attachments = attachments;
//    }
//
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}