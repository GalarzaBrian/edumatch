package com.example.edumatch.retrofit.interfaces;

import com.example.edumatch.retrofit.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("users/new")
    Call<RegisterRequest> createUser(@Body RegisterRequest user);
}
