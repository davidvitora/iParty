package com.iparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.iparty.R;
import com.iparty.model.Bebida;

import java.util.ArrayList;
import java.util.List;

public class BebidasActivity extends AppCompatActivity {

    List<Bebida> bebidas = new ArrayList<Bebida>();
    EditText quantidadeDeAdultos;
    EditText quantidadeDeCriancas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bebidas);
        quantidadeDeAdultos = (EditText) findViewById(R.id.txtQuantidadeAdultos);
        quantidadeDeCriancas = (EditText) findViewById(R.id.txtQuantidadeCriancas);

    }
}
