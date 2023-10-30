package com.example.edumatch.retrofit.interfaces;
import com.example.edumatch.retrofit.model.ProjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface ProjectApi {
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("projects")
    Call<List<ProjectResponse>> getProjects(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("projects")
    Call<ProjectResponse> createProject(@Header("Authorization") String auth, @Body ProjectResponse project);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("projects/{id}")
    Call<ProjectResponse> updateProject(@Header("Authorization") String auth, @Path("id") int projectId, @Body ProjectResponse project);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("projects/{id}")
    Call<Void> deleteProject(@Header("Authorization") String auth, @Path("id") int projectId);
}



