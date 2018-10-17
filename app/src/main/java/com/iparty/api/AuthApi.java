package com.iparty.api;

import com.iparty.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    Retrofit openRetrofit = new Retrofit.Builder()
            .baseUrl("https://iparty-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("api/iparty/login")
    Call<User> login(@Body User user);

    @POST("api/iparty/signup")
    Call<Void> signup(@Body User user);
}
