package com.iparty.api;

import com.iparty.api.interfaces.Auth;
import com.iparty.api.interfaces.StaticRetrofit;
import com.iparty.model.Token;
import com.iparty.model.User;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Maurício Generoso on 11/2/2018
 */
public class AuthApi {

    private final Auth auth;

    public AuthApi(){
        this.auth = StaticRetrofit.RETROFIT.create(Auth.class);
    }

    public void login(User user, Callback<Token> callback){
        Call<Token> call = auth.login(user);
        call.enqueue(callback);
    }

    public void validateToken(Token token, Callback<Void> callback){
        Call<Void> call = auth.validateToken(token);
        call.enqueue(callback);
    }

    public void signup(User user, Callback<Void> callback){
        Call<Void> call = auth.signup(user);
        call.enqueue(callback);
    }

}
