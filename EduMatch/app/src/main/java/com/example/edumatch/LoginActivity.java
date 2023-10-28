package com.example.edumatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edumatch.retrofit.Constants;
import com.example.edumatch.retrofit.interfaces.UserApi;
import com.example.edumatch.retrofit.model.ErrorResponse;
import com.example.edumatch.retrofit.model.LoginRequest;
import com.example.edumatch.retrofit.model.LoginResponse;
import com.example.edumatch.retrofit.model.RegisterResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdt;
    private EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdt = findViewById(R.id.LoginMail);
        passwordEdt = findViewById(R.id.LoginPassword);

        TextView textView = findViewById(R.id.tvRegister);
        Button button = findViewById(R.id.button);

        String texto = textView.getText().toString();

        SpannableString spannableString = new SpannableString(texto);

        int inicioEnlace = texto.indexOf("Registrate");
        int finEnlace = inicioEnlace + "Registrate".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//                startActivity(intent);

                String email = emailEdt.getText().toString();
                String password=passwordEdt.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserApi userApi = retrofit.create(UserApi.class);

                LoginRequest loginRequest = new LoginRequest(email,password);

                Call<LoginResponse> call = userApi.loginUser(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()){

                            LoginResponse loginResponse = response.body();

                            SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Jwt", loginResponse.getJwt());
                            editor.putString("MailUsuario", loginResponse.getEmail());
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Logueo exitoso", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(intent);

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

                            Toast.makeText(LoginActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error inesperado ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });





        spannableString.setSpan(clickableSpan, inicioEnlace, finEnlace, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);

        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    }
