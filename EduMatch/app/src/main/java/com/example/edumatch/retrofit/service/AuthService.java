package com.example.edumatch.retrofit.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.edumatch.HomePageActivity;
import com.example.edumatch.LoginActivity;
import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.UserApi;
import com.example.edumatch.retrofit.model.ErrorResponse;
import com.example.edumatch.retrofit.model.LoginRequest;
import com.example.edumatch.retrofit.model.LoginResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService{



    public static boolean login(String email, String password, Context context) {



        Log.d("login","funcion");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);

        LoginRequest loginRequest = new LoginRequest(email,password);

        Call<LoginResponse> call = userApi.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            boolean isLogged= true;
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){

                    LoginResponse loginResponse = response.body();

                    saveJwt(loginResponse, context);

                    Toast.makeText(context, "Logueo exitoso", Toast.LENGTH_LONG).show();

                    isLogged=true;

                } else{

                    int statusCode = response.code();
                    String errorMessage = "Ocurrio un error";

                    try {
                        Converter<ResponseBody, ErrorResponse> errorConverter =
                                retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                        ErrorResponse error = errorConverter.convert(response.errorBody());
                        errorMessage = error.getMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isLogged=false;
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(context, "sin conexion al servidor", Toast.LENGTH_LONG).show();
                offlineMode(context);
                isLogged=false;
            }
        });

        return false;
    }

    private boolean isTrue(boolean value){
        return value;
    };
    private static void saveJwt(LoginResponse loginResponse, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Jwt", loginResponse.jwt);
        editor.putString("MailUsuario", loginResponse.getEmail());
        editor.putBoolean("isOffline", false);
        editor.apply();
    }

    private static void offlineMode(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("online", false);
        editor.apply();
    }

}
