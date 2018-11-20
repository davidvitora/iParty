package com.iparty;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class GuestActivity extends AppCompatActivity {
  private TextView txt_Nome;
  private RadioButton masc;
  private RadioButton fem;
  private RadioButton child;
  private Button add;
  private String tipo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guests);

        txt_Nome = (TextView) findViewById(R.id.textNome);
        masc = (RadioButton) findViewById(R.id.homem);
        fem = (RadioButton) findViewById(R.id.mulher);
        child = (RadioButton) findViewById(R.id.crianca);
        add = (Button) findViewById(R.id.add);

        masc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = "Homem";
            }
        });
        fem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tipo = "Mulher";
            }
        });
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tipo = "Criança";
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = (String) txt_Nome.getText();

                //Salvar Banco Convidado
            }
        });

    }
}
