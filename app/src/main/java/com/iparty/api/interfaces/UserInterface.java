package com.iparty.api.interfaces;

import com.iparty.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Maurício Generoso on 11/15/2018
 */
public interface UserInterface {

    @GET("api/v1/users")
    Call<User> getUser(@Header("Authorization") String authorization);

    @Multipart
    @POST("api/upload")
    Call<ResponseBody> setImage(@Part("description") RequestBody description, @Part MultipartBody.Part file, @Header("Authrization") String authrization);
}
