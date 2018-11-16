package com.iparty.api;

import android.content.Context;

import com.iparty.R;
import com.iparty.Utilities.Storage;
import com.iparty.api.interfaces.StaticRetrofit;
import com.iparty.api.interfaces.UserInterface;
import com.iparty.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UserApi {

    private final UserInterface userInterface;

    public UserApi(){
        this.userInterface = StaticRetrofit.RETROFIT.create(UserInterface.class);
    }

    public void getUser(Context context, Callback<User> callback){
        String token = Storage.get(context, context.getString(R.string.storage_iparty_token_key));
        Call<User> call = userInterface.getUser(token);
        call.enqueue(callback);
    }

    public void setImage(Context context, RequestBody description, MultipartBody.Part file, Callback<ResponseBody> callback){
        String token = Storage.get(context, context.getString(R.string.storage_iparty_token_key));
        Call<ResponseBody> call = userInterface.setImage(description, file, token);
        call.enqueue(callback);
    }
}
