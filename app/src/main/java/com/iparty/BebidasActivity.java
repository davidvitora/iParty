package com.iparty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.Utilities.BebidaRow;
import com.iparty.Utilities.Numeric;
import com.iparty.activities.common.BaseActivity;
import com.iparty.model.Bebida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BebidasActivity extends BaseActivity {

    Button btnAdicionarBebida;
    List<Bebida> bebidas = new ArrayList<Bebida>();
    List<BebidaRow> bebidasRowLista = new ArrayList<BebidaRow>();
    EditText quantidadeDeAdultos;
    EditText quantidadeDeCriancas;
    TableLayout listBebidas;

    protected void addBebidaNaTela(Bebida bebida, TableLayout table){
        final BebidaRow tr = new BebidaRow(this, bebida);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.bin);
        i.setPadding(5, 50, 5, 50);
        i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(i);

        final Bebida bebidaLocal = bebida;
        final String nome = bebida.getNome();
        final Float preco_por_litro = bebida.getPreco_por_litro();
        final boolean alcoolica = bebida.isAlcoolica();

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bebidas.remove(bebidaLocal);
                    bebidasRowLista.remove(tr);
                    salvarListaAtual();
                    atualizarLista();
                } catch (JSONException e) {
                    e.printStackTrace();
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
        b.setText("");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);

        b = new TextView(this);
        b.setText("10");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);


        table.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        bebidasRowLista.add(tr);
    }

    public void salvarListaAtual() throws JSONException {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(BebidasActivity.this);
        JSONArray listaBebidasJSON = new JSONArray();
        for(Bebida bebida : bebidas){
            JSONObject bebidaJSON = new JSONObject();
            bebidaJSON.put("nome", bebida.getNome());
            bebidaJSON.put("preco_por_litro", "1");
            if (bebida.isAlcoolica() == true) {
                bebidaJSON.put("alcoolica", "true");
            } else {
                bebidaJSON.put("alcoolica", "false");
            }
            bebidaJSON.put("quantidade_por_pessoa", "1");

            listaBebidasJSON.put(bebidaJSON);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("bebidas_salvas", listaBebidasJSON.toString());
            edit.apply();
        }
    }

    public void atualizarLista(){
        for(BebidaRow bebidaRow : bebidasRowLista){
            Float quantidadeRecomendada = 0f;
            Float precoTotal = 0f;
            if(Numeric.isNumeric(this.quantidadeDeAdultos.getText().toString())) {
                quantidadeRecomendada = Float.parseFloat(this.quantidadeDeAdultos.getText().toString()) * bebidaRow.getBebida().getQuantiodade_por_pessoa();
                precoTotal = quantidadeRecomendada * bebidaRow.getBebida().getPreco_por_litro();
                if(bebidaRow.getBebida().isAlcoolica() == false && Numeric.isNumeric(this.quantidadeDeCriancas.getText().toString())) {
                    quantidadeRecomendada += Float.parseFloat(this.quantidadeDeCriancas.getText().toString()) * bebidaRow.getBebida().getQuantiodade_por_pessoa();
                    precoTotal = quantidadeRecomendada * bebidaRow.getBebida().getPreco_por_litro();
                }
            }
            TextView textView = (TextView) bebidaRow.getChildAt(2);
            textView.setText(Float.toString(quantidadeRecomendada));

            textView = (TextView) bebidaRow.getChildAt(3);
            textView.setText(Float.toString(precoTotal));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CadastroBebidaActivity.updateActivity(this);
        setContentView(R.layout.bebidas);
        btnAdicionarBebida = (Button) findViewById(R.id.btnAdicionarBebida);
        btnAdicionarBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(CadastroBebidaActivity.class);
            }
        });
        quantidadeDeAdultos = (EditText) findViewById(R.id.txtQuantidadeAdultos);
        quantidadeDeAdultos.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizarLista();
            }
        });
        quantidadeDeCriancas = (EditText) findViewById(R.id.txtQuantidadeCriancas);
        quantidadeDeCriancas.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                atualizarLista();
            }
        });
        listBebidas = (TableLayout) findViewById(R.id.listBebidas);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(BebidasActivity.this);
        JSONArray listaBebidasJSON = new JSONArray();
        try {
            listaBebidasJSON = new JSONArray(preferences.getString("bebidas_salvas","[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int n = 0; n < listaBebidasJSON.length(); n++)
        {
            try {
                JSONObject object = listaBebidasJSON.getJSONObject(n);
                bebidas.add(new Bebida(object.getString("nome"), Float.valueOf(object.getString("preco_por_litro")),
                        Boolean.valueOf(object.getString("alcoolica")), Float.valueOf(object.getString("quantidade_por_pessoa"))));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(Bebida bebida : bebidas){
            this.addBebidaNaTela(bebida, listBebidas);
        }

        atualizarLista();
    }

    @Override
    public void onClick(View view) {

    }

}
