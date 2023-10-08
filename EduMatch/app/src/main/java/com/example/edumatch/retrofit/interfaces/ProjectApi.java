package com.example.edumatch.retrofit.interfaces;

import com.example.edumatch.retrofit.model.ProjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ProjectApi {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("projects")
    Call<List<ProjectResponse>> getProjects(@Header("Authorization") String auth);
}
