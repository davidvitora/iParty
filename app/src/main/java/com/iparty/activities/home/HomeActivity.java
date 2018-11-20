package com.iparty.activities.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.MainActivity;
import com.iparty.Utilities.Storage;
import com.iparty.activities.party.CreatePartyActivity;
import com.iparty.R;
import com.iparty.Utilities.Globals;
import com.iparty.api.UserApi;
import com.iparty.model.User;
import com.iparty.activities.common.BaseActivity;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private static class ViewHolder {
        TextView textUsername;
        LinearLayout linearCreateEvent;
        ImageView imageUserPicture;
        ImageView exit;
        private static class EventoRecente {
            TextView eventoRecenteDias;
            TextView eventoRecenteNome;
            TextView eventoRecenteConvidados;
            TextView eventoRecenteData;
            TextView eventoRecenteLocal;
            TextView eventoRecenteValor;
            ImageView eventoRecenteFoto;
            Button eventoMaisInformacoes;
        }
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ViewHolder.EventoRecente evento = new ViewHolder.EventoRecente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.viewHolder.textUsername = findViewById(R.id.textUserName);
        this.viewHolder.imageUserPicture = findViewById(R.id.imageUserPicture);
        this.viewHolder.linearCreateEvent = findViewById(R.id.linearCreateEvent);
        this.viewHolder.exit= findViewById(R.id.exit);

        this.evento.eventoRecenteNome = findViewById(R.id.eventoRecenteNome);
        this.evento.eventoRecenteConvidados= findViewById(R.id.eventoRecenteConvidados);
        this.evento.eventoRecenteData = findViewById(R.id.eventoRecenteData);
        this.evento.eventoRecenteDias = findViewById(R.id.eventoRecenteDias);
        this.evento.eventoRecenteLocal = findViewById(R.id.eventoRecenteLocal);
        this.evento.eventoRecenteValor = findViewById(R.id.eventoRecenteValor);
        this.evento.eventoRecenteFoto = findViewById(R.id.eventoRecenteFoto);
        this.evento.eventoMaisInformacoes = findViewById(R.id.eventoMaisInformacoes);

        this.viewHolder.linearCreateEvent.setOnClickListener(this);
        this.viewHolder.imageUserPicture.setOnClickListener(this);
        this.viewHolder.exit.setOnClickListener(this);
        this.evento.eventoMaisInformacoes.setOnClickListener(this);

        loadImageUser();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.txtNoiva:
                break;
            case R.id.linearCreateEvent:
                goTo(CreatePartyActivity.class);
                break;
            case R.id.imageUserPicture:
                goTo(PictureActivity.class);
                break;
            case R.id.exit:
                Storage.set(HomeActivity.this, getString(R.string.storage_iparty_email_key), null);
                Storage.set(HomeActivity.this, getString(R.string.storage_iparty_remember_key), Boolean.FALSE.toString());
                Storage.set(HomeActivity.this, getString(R.string.storage_iparty_token_key), null);
                goTo(MainActivity.class);
                break;
            case R.id.eventoMaisInformacoes:

                break;
        }
    }

    private void loadImageUser(){
        new UserApi().getUser(this, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    User user = response.body();
                    viewHolder.textUsername.setText(user.getName());
                    Picasso.get().load(Globals.BUCKET_URL  + user.getId() + Globals.FILE_EXTENSION).into(viewHolder.imageUserPicture);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
