package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.ProjectApi;
import com.example.edumatch.retrofit.interfaces.UserApi;
import com.example.edumatch.retrofit.model.ProjectResponse;
import com.example.edumatch.utils.ProjectAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    List<ProjectResponse> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_search);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
    String jwtToken = sharedPreferences.getString("Jwt", null);

     String auth = "Bearer " + jwtToken ;
        recyclerView = findViewById(R.id.rvProjects);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProjectApi projectApi = retrofit.create(ProjectApi.class);

        Call<List<ProjectResponse>> call = projectApi.getProjects(auth);
        Log.d("TAG", "inicio activity");
        call.enqueue(new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                if(response.isSuccessful()){
                    Log.d("TAG", "hay respuesta");
                    datalist = response.body();
                    recyclerView.setLayoutManager( new LinearLayoutManager(ProjectSearchActivity.this));
                    ProjectAdapter adapter = new ProjectAdapter(datalist);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable t) {

            }
        });



    }
}