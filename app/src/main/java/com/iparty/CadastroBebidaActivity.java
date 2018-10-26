package com.iparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import com.iparty.R;

public class CadastroBebidaActivity extends AppCompatActivity {

    EditText nomeBebida;
    EditText precoPorLitro;
    EditText quantidadePorPessoa;
    CheckBox checkAlcoolico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_bebida);
        nomeBebida = (EditText) findViewById(R.id.txtNomeBebida);
        precoPorLitro = (EditText) findViewById(R.id.txtPrecoPorLitro);
        quantidadePorPessoa = (EditText) findViewById(R.id.txtQuantidadePorPessoa);
        checkAlcoolico = (CheckBox) findViewById(R.id.checkAlcoolico);

    }
}
