package com.example.edumatch.retrofit.interfaces;

import com.example.edumatch.retrofit.model.LoginRequest;
import com.example.edumatch.retrofit.model.LoginResponse;
import com.example.edumatch.retrofit.model.RegisterRequest;
import com.example.edumatch.retrofit.model.RegisterResponse;
import com.example.edumatch.retrofit.model.UserProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    @POST("auth/register")
    Call<RegisterResponse> createUser(@Body RegisterRequest user);

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("auth/me")
    Call<UserProfileResponse> meData(@Header("Authorization") String auth);
}



