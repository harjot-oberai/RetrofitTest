package com.example.harjot.retrofittest.api;

import com.example.harjot.retrofittest.models.GitResult;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Harjot on 06-Apr-16.
 */
public interface GitApiInterface {

    @GET("/search/users")
    Call<GitResult> getUsersNamedTom(@Query("q") String name);
}
