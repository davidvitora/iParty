package com.iparty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.iparty.R;
import com.iparty.model.Bebida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.*;

public class CadastroBebidaActivity extends AppCompatActivity {

    ArrayList<Bebida> bebidas_precadastradas = new ArrayList<>();
    EditText nomeBebida;
    EditText precoPorLitro;
    EditText quantidadePorPessoa;
    CheckBox checkAlcoolico;
    TableLayout listBebidasPrecadastradas;

    protected void addBebidasPrecadastradasNaTela(Bebida bebida, TableLayout table){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.copy);
        i.setPadding(5, 50, 5, 50);
        i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(i);

        final String nome = bebida.getNome();
        final Float preco_por_litro = bebida.getPreco_por_litro();
        final boolean alcoolica = bebida.isAlcoolica();

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeBebida.setText(nome);
                precoPorLitro.setText(Float.toString(preco_por_litro));
                if(alcoolica) {
                    checkAlcoolico.setChecked(true);
                }
            }
        });

        TextView b = new TextView(this);
        b.setText(bebida.getNome());
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);

        b = new TextView(this);
        b.setText(Float.toString(bebida.getPreco_por_litro()));
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);

        b = new TextView(this);
        if(bebida.isAlcoolica()) {
            b.setText("Sim");
        } else {
            b.setText("Nao");
        }
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);


        table.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_bebida);
        nomeBebida = (EditText) findViewById(R.id.txtNomeBebida);
        precoPorLitro = (EditText) findViewById(R.id.txtPrecoPorLitro);
        quantidadePorPessoa = (EditText) findViewById(R.id.txtQuantidadePorPessoa);
        checkAlcoolico = (CheckBox) findViewById(R.id.checkAlcoolico);
        listBebidasPrecadastradas = (TableLayout) findViewById(R.id.listBebidasPrecadastradas);

        bebidas_precadastradas.add(new Bebida("skollbeats", 3f, true));
        bebidas_precadastradas.add(new Bebida("skoll", 2f,true));
        bebidas_precadastradas.add(new Bebida("suco de uva", 0.5f, false));
        for(Bebida bebida : bebidas_precadastradas){
            this.addBebidasPrecadastradasNaTela(bebida, listBebidasPrecadastradas);
        }

    }
}
