package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iparty.Utilities.Globals;
import com.iparty.Utilities.Storage;
import com.iparty.api.AuthApi;
import com.iparty.api.UserApi;
import com.iparty.enums.Festa;
import com.iparty.model.Convidado;
import com.iparty.model.Evento;
import com.iparty.model.User;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        TextView txtvNomeUsuario;
        LinearLayout btnCriarEvento;
        ImageView userPicture;
        LinearLayout linearSemEventos;
        LinearLayout linearComEvento;
        TextView eventoRecenteNome;
        TextView eventoRecenteLocal;
        TextView eventoRecenteDias;
        TextView eventoRecenteConvidados;
        TextView eventoRecenteData;
        TextView eventoRecenteValor;
        ImageView eventoRecenteFoto;

    }

    private Evento evento = new Evento(1, "Festa da Dirce", new Date(1543360295000L), Festa.CASAMENTO, "Rua do Cabeça de Gelo", 2300.0);

    private UserApi userApi;
    private User user;
    private ViewHolder viewHolder = new ViewHolder();
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        this.evento.addConvidado(new Convidado(1, "Carlos"));
        this.evento.addConvidado(new Convidado(2, "Cleberso"));
        this.evento.addConvidado(new Convidado(3, "Joberson"));
        this.evento.addConvidado(new Convidado(4, "Ana"));
        this.evento.addConvidado(new Convidado(5, "Maria"));
        this.evento.addConvidado(new Convidado(6, "Bisca"));
        this.evento.addConvidado(new Convidado(7, "Kaka"));
        this.evento.addConvidado(new Convidado(8, "Dirce"));


        this.userApi = AuthApi.RETROFIT.create(UserApi.class);
        this.viewHolder.txtvNomeUsuario = findViewById(R.id.txtvNomeUsuario);
        this.viewHolder.userPicture = findViewById(R.id.userPicture);
        this.viewHolder.linearSemEventos = findViewById(R.id.linearSemEventos);
        this.viewHolder.linearSemEventos = findViewById(R.id.linearSemEventos);
        this.viewHolder.linearComEvento = findViewById(R.id.linearComEvento);
        this.viewHolder.eventoRecenteNome = findViewById(R.id.eventoRecenteNome);
        this.viewHolder.eventoRecenteLocal = findViewById(R.id.eventoRecenteLocal);
        this.viewHolder.eventoRecenteDias = findViewById(R.id.eventoRecenteDias);
        this.viewHolder.eventoRecenteConvidados = findViewById(R.id.eventoRecenteConvidados);
        this.viewHolder.eventoRecenteData = findViewById(R.id.eventoRecenteData);
        this.viewHolder.eventoRecenteValor = findViewById(R.id.eventoRecenteValor);
        this.viewHolder.eventoRecenteFoto = findViewById(R.id.eventoRecenteFoto);


        final String token = Storage.get(HomeActivity.this, getString(R.string.storage_iparty_token_key));

        Call<User> call = userApi.getUser(token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    user = response.body();
                    Storage.set(HomeActivity.this, getString(R.string.storage_iparty_user), new Gson().toJson(user.getJson()));
                    viewHolder.txtvNomeUsuario.setText(user.getName());
                    Picasso.get().load(Globals.BUCKET_URL + user.getId() + Globals.FILE_EXTENSION).into(viewHolder.userPicture);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });

        if (evento != null) {
            this.viewHolder.linearComEvento.setVisibility(View.VISIBLE);
            this.viewHolder.linearSemEventos.setVisibility(View.GONE);
            setarEventoView();
        } else {
            this.viewHolder.linearComEvento.setVisibility(View.GONE);
            this.viewHolder.linearSemEventos.setVisibility(View.VISIBLE);
        }

        this.viewHolder.btnCriarEvento = findViewById(R.id.btnCriarEvento);
        this.viewHolder.btnCriarEvento.setOnClickListener(this);
        this.viewHolder.userPicture.setOnClickListener(this);

    }

    private static String getTimeLeft(Date data) { // dateFormat = "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(data);
        String[] DateSplit = date.split("-");
        int month = Integer.parseInt(DateSplit[1]) - 1, // if month is november  then subtract by 1
                year = Integer.parseInt(DateSplit[0]), day = Integer
                .parseInt(DateSplit[2]), hour = 0, minute = 0, second = 0;
        Calendar now = Calendar.getInstance();

        int sec = second - Calendar.getInstance().get(Calendar.SECOND), min = minute
                - Calendar.getInstance().get(Calendar.MINUTE), hr = hour
                - Calendar.getInstance().get(Calendar.HOUR_OF_DAY), dy = day
                - Calendar.getInstance().get(Calendar.DATE), mnth = month
                - Calendar.getInstance().get(Calendar.MONTH), daysinmnth = 32 - dy;

        Calendar end = Calendar.getInstance();

        end.set(year, month, day);

        if (mnth != 0) {
            if (dy != 0) {
                if (sec < 0) {
                    sec = (sec + 60) % 60;
                    min--;
                }
                if (min < 0) {
                    min = (min + 60) % 60;
                    hr--;
                }
                if (hr < 0) {
                    hr = (hr + 24) % 24;
                    dy--;
                }
                if (dy < 0) {
                    dy = (dy + daysinmnth) % daysinmnth;
                    mnth--;
                }
                if (mnth < 0) {
                    mnth = (mnth + 12) % 12;
                }
            }
        }

        String hrtext = (hr == 1) ? "hora" : "horas", dytext = (dy == 1) ? "dia" : "dias", mnthtext = (mnth == 1) ? "mês" : "meses";

        if (now.after(end)) {
            return "Agora!";
        } else {
            String months = "", days = "", hours = "";
            months = (mnth > 0) ? mnth + " " + mnthtext : "";
            if (mnth <= 0) {
                days = (dy > 0) ? dy + " " + dytext : "";
                if (dy <= 0) {
                    hours = (hr > 0) ? hr + " " + hrtext : "";
                }
            }
            if(months == days && days == hours)
                return "Agora!";

            return months + days + hours + " faltando";
        }
    }

    private void setarEventoView() {
        this.viewHolder.eventoRecenteNome.setText(this.evento.getTitulo());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(this.evento.getData());
        this.viewHolder.eventoRecenteData.setText(date);
        this.viewHolder.eventoRecenteLocal.setText(this.evento.getEndereço());
        this.viewHolder.eventoRecenteValor.setText(String.format(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(this.evento.getValor())));
        this.viewHolder.eventoRecenteConvidados.setText("" + this.evento.getConvidados().size());

        this.viewHolder.eventoRecenteDias.setText(this.getTimeLeft(this.evento.getData()));

        switch (this.evento.getTipo()) {
            case ANIVERSARIO:
                this.viewHolder.eventoRecenteFoto.setImageResource(R.drawable.cake);
                break;
            case CASAMENTO:
                this.viewHolder.eventoRecenteFoto.setImageResource(R.drawable.ring);
                break;
        }
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
