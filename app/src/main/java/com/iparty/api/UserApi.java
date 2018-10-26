package com.iparty.api;

import com.iparty.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserApi {

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("https://iparty-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("api/v1/users")
    Call<User> getUser(@Header("Authorization") String authorization);

}
