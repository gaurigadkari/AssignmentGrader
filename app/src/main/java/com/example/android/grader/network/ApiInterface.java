package com.example.android.grader.network;

import com.example.android.grader.models.Assignment;
import com.example.android.grader.models.Submission;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public interface ApiInterface {

    @Headers("Content-Type: text/json")
    @GET("assignments?")
    Call<List<Assignment>> getAssignmentResults(@Query("access_token") String accessToken);

    @Headers("Content-Type: text/json")
    @GET("assignment_submissions")
    Call<List<Submission>> getSubmissionResults(@Query("assignment_id") int groupId, @Query("access_token") String accessToken);

    @GET("assignments")
    Call<ResponseBody> getAssignmentResults(@QueryMap Map<String, String> options);
}
