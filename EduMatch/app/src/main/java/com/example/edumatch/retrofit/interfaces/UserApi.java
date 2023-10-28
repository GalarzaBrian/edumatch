package com.example.edumatch.retrofit.interfaces;

import com.example.edumatch.retrofit.model.LoginRequest;
import com.example.edumatch.retrofit.model.LoginResponse;
import com.example.edumatch.retrofit.model.RegisterRequest;
import com.example.edumatch.retrofit.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {
    @POST("auth/register")
    Call<RegisterResponse> createUser(@Body RegisterRequest user);

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest user);
}



