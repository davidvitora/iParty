package com.iparty.api.interfaces;

import com.iparty.model.Token;
import com.iparty.model.User;
import com.iparty.model.ForgetPassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Maurício Generoso on 18/10/2018
 */
public interface AuthInterface {

    @POST("api/login")
    Call<Token> login(@Body User user);

    @POST("api/validateToken")
    Call<Void> validateToken(@Body Token token);

    @POST("api/signup")
    Call<Void> signup(@Body User user);

    @POST("api/forgetpassword")
    Call<Token> forgetPassword(@Body User user);

    @POST("api/forgetpassword/validateCode")
    Call<Void> validateCode(@Body ForgetPassword forgetPassword);

    @POST("api/changePassword")
    Call<Void> changePassword(@Body ForgetPassword forgetPassword);
}
