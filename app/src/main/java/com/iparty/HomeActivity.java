package com.iparty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.Utilities.Globals;
import com.iparty.api.UserApi;
import com.iparty.model.User;
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
    }

    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.viewHolder.textUsername = findViewById(R.id.textUserName);
        this.viewHolder.imageUserPicture = findViewById(R.id.imageUserPicture);
        this.viewHolder.linearCreateEvent = findViewById(R.id.linearCreateEvent);

        this.viewHolder.linearCreateEvent.setOnClickListener(this);
        this.viewHolder.imageUserPicture.setOnClickListener(this);

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
