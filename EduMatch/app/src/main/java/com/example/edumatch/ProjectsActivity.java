package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.edumatch.retrofit.interfaces.ProjectApi;
import com.example.edumatch.retrofit.model.ProjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectsActivity extends AppCompatActivity {

    List<ProjectResponse> projects;
    private String auth = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBtYWlsIiwiZXhwIjoxNjk2ODE0ODU5LCJpYXQiOjE2OTY3Mjg0NTl9.tKCslRoRe5D0XWDHjrlpC0aUpfiRmJxIM9llPZkKdsk" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        getProjects();
    }

    private void getProjects(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProjectApi service = retrofit.create(ProjectApi.class);

        Call<List<ProjectResponse>> call = service.getProjects(auth);
        call.enqueue(new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                if(!response.isSuccessful()){
                    Log.e("respuesta error",response.message());
                }
                projects = response.body();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    projects.forEach(p -> Log.i("El Proyecto es: ", p.toString()));
                }
                Toast.makeText(ProjectsActivity.this, " "+response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable t) {
                Log.e("error on failure",t.getMessage());
            }
        });
    }

}