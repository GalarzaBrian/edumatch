package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.UserApi;
import com.example.edumatch.retrofit.model.UserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfile extends AppCompatActivity {

    Button btn_home;
    private TextView nombre;
    private TextView email;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        String jwtToken = sharedPreferences.getString("Jwt", null);

        String auth = "Bearer " + jwtToken ;

        btn_home =findViewById(R.id.button_home);
        nombre = findViewById(R.id.textView6);
        email= findViewById(R.id.textView7);
        about= findViewById(R.id.textView9);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);

        Call<UserProfileResponse> call = userApi.meData(auth);

        call.enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                if(response.isSuccessful()){
                    Log.d("TAG","respuesta"+response.body());
                    nombre.setText(response.body().getNombre());
                    email.setText(response.body().getMail());
                    about.setText(response.body().getAbout());
                }
            Log.d("Responde","pero pasa algo");
            }


            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
    Log.d("tag error", t.getMessage());
            }
        });



        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}