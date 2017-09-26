package com.example.android.grader.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gauri Gadkari on 9/22/17.
 */

public class RetrofitClient {
    private static RetrofitClient instance = null;
    public static final String BASE_URL = "https://api.edmodo.com/";
    public static final String ACCESS_TOKEN_VALUE = "12e7eaf1625004b7341b6d681fa3a7c1c551b5300cf7f7f3a02010e99c84695d";
    public static final int ERROR_CODE_TOO_MANY_REQUESTS = 429;

    // Keep your services here, build them in buildRetrofit method later
    private ApiInterface apiInterface;
    private Retrofit retrofit;

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // Build retrofit once when creating a single instance
    private RetrofitClient() {
        // Implement a method to build your retrofit
        buildRetrofit();
    }

    private void buildRetrofit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Build your services once
        this.apiInterface = retrofit.create(ApiInterface.class);
    }
}
