package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        this.viewHolder.txtvNomeUsuario = findViewById(R.id.txtvNomeUsuario);
        this.viewHolder.userPicture = findViewById(R.id.userPicture);

        this.userApi = new UserApi();

        this.userApi.getUser(this, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    user = response.body();
                    System.out.println(user);
                    viewHolder.txtvNomeUsuario.setText(user.getName());
                    Picasso.get().load(Globals.BUCKET_URL  + user.getId() + Globals.FILE_EXTENSION).into(viewHolder.userPicture);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });

        this.viewHolder.btnCriarEvento = findViewById(R.id.btnCriarEvento);
        this.viewHolder.btnCriarEvento.setOnClickListener(this);
        this.viewHolder.userPicture.setOnClickListener(this);
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
            case R.id.userPicture:
                Intent itPicture = new Intent(HomeActivity.this, PictureActivity.class);
                startActivity(itPicture);
                break;
        }
    }

}
