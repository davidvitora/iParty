package com.iparty.api;

import com.iparty.model.Token;
import com.iparty.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Maurício Generoso on 18/10/2018
 */
public interface AuthApi {

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("http://9f94b2b9.ngrok.io/")
            //.baseUrl("http://172.23.8.121:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("api/login")
    Call<Token> login(@Body User user);

    @POST("api/validateToken")
    Call<Void> validateToken(@Body Token token);

    @POST("api/signup")
    Call<Void> signup(@Body User user);
}
