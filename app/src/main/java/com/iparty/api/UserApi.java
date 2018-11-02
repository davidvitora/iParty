package com.iparty.api;

import com.iparty.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserApi {

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl("http://9f94b2b9.ngrok.io/")
            //.baseUrl("http://172.23.8.121:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("api/v1/users")
    Call<User> getUser(@Header("Authorization") String authorization);

    @Multipart
    @POST("api/v1/users/upload")
    Call<ResponseBody> setImage(@Part("description") RequestBody description, @Part MultipartBody.Part file, @Header("Authrization") String authrization);
}
