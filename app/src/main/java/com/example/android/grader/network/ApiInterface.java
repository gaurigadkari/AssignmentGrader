package com.example.android.grader.network;

import com.example.android.grader.models.Assignment;
import com.example.android.grader.models.Submission;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface ApiInterface {

    @Headers("Content-Type: text/json")
    @GET("assignments?")
    Call<List<Assignment>> getAssignmentResults(@Query("access_token") String accessToken);

    @Headers("Content-Type: text/json")
    @GET("assignment_submissions")
    Call<List<Submission>> getSubmissionResults(@Query("assignment_id") int groupId, @Query("access_token") String accessToken);
}
