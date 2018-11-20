package com.iparty.api.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maurício Generoso on 25/10/2018
 */
public interface StaticRetrofit {

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("https://iparty-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
