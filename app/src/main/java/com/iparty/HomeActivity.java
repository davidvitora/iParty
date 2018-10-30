package com.iparty;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.Utilities.Storage;
import com.iparty.api.AuthApi;
import com.iparty.api.UserApi;
import com.iparty.model.User;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        TextView txtvNomeUsuario;
        LinearLayout btnCriarEvento;
        ImageView userPicture;
    }
    private UserApi userApi;
    private User user;
    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        this.userApi = AuthApi.RETROFIT.create(UserApi.class);
        this.viewHolder.txtvNomeUsuario = findViewById(R.id.txtvNomeUsuario);
        this.viewHolder.userPicture = findViewById(R.id.userPicture);

        final String token = Storage.get(HomeActivity.this, getString(R.string.storage_iparty_token_key));

        Call<User> call = userApi.getUser(token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    user = response.body();
                    System.out.println(user);
                    viewHolder.txtvNomeUsuario.setText(user.getName());
                    Picasso.get().load(user.getImgSrc()).into(viewHolder.userPicture);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });

        this.viewHolder.btnCriarEvento = findViewById(R.id.btnCriarEvento);
        this.viewHolder.btnCriarEvento.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.txtNoiva:
                break;
            case R.id.btnCriarEvento:
                 Intent itRegister = new Intent(HomeActivity.this, CadastroFestaActivity.class);
                 startActivity(itRegister);
                break;
            case R.id.text_forget_password:
                break;
        }
    }
}
