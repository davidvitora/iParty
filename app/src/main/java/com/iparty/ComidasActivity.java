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

import com.iparty.Utilities.ComidaRow;
import com.iparty.Utilities.Numeric;
import com.iparty.activities.common.BaseActivity;
import com.iparty.model.Comida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ComidasActivity extends BaseActivity {

    Button btnAdicionarComida;
    List<Comida> comidas = new ArrayList<Comida>();
    List<ComidaRow> comidasRowLista = new ArrayList<ComidaRow>();
    EditText quantidadeDeAdultos;
    EditText quantidadeDeCriancas;
    TableLayout listComidas;

    protected void addComidaNaTela(Comida comida, TableLayout table){
        final ComidaRow tr = new ComidaRow(this, comida);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.bin);
        i.setPadding(5, 50, 5, 50);
        i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(i);

        final Comida comidaLocal = comida;
        final String nome = comida.getNome();
        final Float preco_por_litro = comida.getPreco();

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    comidas.remove(comidaLocal);
                    comidasRowLista.remove(tr);
                    salvarListaAtual();
                    atualizarLista();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        TextView b = new TextView(this);
        b.setText(comida.getNome());
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
        comidasRowLista.add(tr);
    }

    public void salvarListaAtual() throws JSONException {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(ComidasActivity.this);
        JSONArray listComidasJSON = new JSONArray();
        for(Comida comida: comidas){
            JSONObject comidaJSON = new JSONObject();
            comidaJSON.put("nome", comida.getNome());
            comidaJSON.put("preco", "1");
            comidaJSON.put("quantidade_por_pessoa", "1");

            listComidasJSON.put(comidaJSON);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("comidas_salvas", listComidasJSON.toString());
            edit.apply();
        }
    }

    public void atualizarLista(){
        for(ComidaRow comidaRow : comidasRowLista){
            Float quantidadeRecomendada = 0f;
            Float precoTotal = 0f;
            if(Numeric.isNumeric(this.quantidadeDeAdultos.getText().toString())) {
                quantidadeRecomendada = Float.parseFloat(this.quantidadeDeAdultos.getText().toString()) * comidaRow.getComida().getQuantiodade_por_pessoa();
                precoTotal = quantidadeRecomendada * comidaRow.getComida().getPreco();
                if(Numeric.isNumeric(this.quantidadeDeCriancas.getText().toString())) {
                    quantidadeRecomendada += Float.parseFloat(this.quantidadeDeCriancas.getText().toString()) * comidaRow.getComida().getQuantiodade_por_pessoa();
                    precoTotal = quantidadeRecomendada * comidaRow.getComida().getPreco();
                }
            }
            TextView textView = (TextView) comidaRow.getChildAt(2);
            textView.setText(Float.toString(quantidadeRecomendada));

            textView = (TextView) comidaRow.getChildAt(3);
            textView.setText(Float.toString(precoTotal));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CadastroComidaActivity.updateActivity(this);
        setContentView(R.layout.comidas);
        btnAdicionarComida = (Button) findViewById(R.id.btnAdicionarComida);
        btnAdicionarComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(CadastroComidaActivity.class);
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
        listComidas = (TableLayout) findViewById(R.id.listComidas);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(ComidasActivity.this);
        JSONArray listComidasJSON = new JSONArray();
        try {
            listComidasJSON = new JSONArray(preferences.getString("comidas_salvas","[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int n = 0; n < listComidasJSON.length(); n++)
        {
            try {
                JSONObject object = listComidasJSON.getJSONObject(n);
                comidas.add(new Comida(object.getString("nome"), Float.valueOf(object.getString("preco")),
                        Float.valueOf(object.getString("quantidade_por_pessoa"))));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for(Comida comida: comidas){
            this.addComidaNaTela(comida, listComidas);
        }

        atualizarLista();
    }

    @Override
    public void onClick(View view) {

    }

}
