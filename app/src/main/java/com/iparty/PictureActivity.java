package com.iparty;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.Utilities.Globals;
import com.iparty.Utilities.Storage;
import com.iparty.api.AuthApi;
import com.iparty.api.UserApi;
import com.iparty.model.User;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        Button setPicture;
        ImageView profileImage;
    }

    private UserApi userApi;
    private User user;
    private ViewHolder viewHolder = new ViewHolder();
    public static final int PICK_IMAGE = 1;
    private Intent intentData = new Intent();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_picture);
        this.userApi = new UserApi();
        this.viewHolder.setPicture = findViewById(R.id.setPicture);
        this.viewHolder.profileImage = findViewById(R.id.profileImage);

        this.userApi.getUser(this, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    user = response.body();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PictureActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });

        this.viewHolder.setPicture.setOnClickListener(this);
        this.viewHolder.profileImage.setOnClickListener(this);
        //async aqui
        //Picasso.get().load(Globals.BUCKET_URL  + user.getId() + Globals.FILE_EXTENSION).into(viewHolder.profileImage);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            Uri uri = data.getData();
            System.out.println(uri);
            this.viewHolder.profileImage.setImageURI(uri);
            String token = Storage.get(PictureActivity.this, getString(R.string.storage_iparty_token_key));
            System.out.println(token);
            File upFile = new File(uri.getPath());
            System.out.println(getContentResolver().getType(uri));
            RequestBody reqFile = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), upFile);
            System.out.println(reqFile.toString());
            RequestBody description = RequestBody.create(okhttp3.MultipartBody.FORM, "dirce");
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", upFile.getName(), reqFile);


            this.userApi.setImage(this, description, body, new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    // Do Something
                    System.out.println("é a nova era, petista");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setPicture:
                if (this.intentData != null) {
                    this.uploadFile(this.intentData.getData());
                }
                break;
            case R.id.profileImage:
                this.intentData.setType("image/*");
                this.intentData.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(this.intentData, "Select Picture"), PICK_IMAGE);
                break;
        }
    }

    private void uploadFile(Uri uri) {

    }
}
