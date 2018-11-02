package com.iparty.api.interfaces;

import com.iparty.model.Token;
import com.iparty.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Maurício Generoso on 18/10/2018
 */
public interface Auth {

    @POST("api/login")
    Call<Token> login(@Body User user);

    @POST("api/validateToken")
    Call<Void> validateToken(@Body Token token);

    @POST("api/signup")
    Call<Void> signup(@Body User user);
}
