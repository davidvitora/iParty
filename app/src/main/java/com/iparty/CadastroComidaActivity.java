package com.iparty;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.Utilities.Numeric;
import com.iparty.activities.common.BaseActivity;
import com.iparty.model.Comida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CadastroComidaActivity extends BaseActivity {

    private static WeakReference<Activity> referenciaComidas;
    public static void updateActivity(Activity activity) {
        referenciaComidas = new WeakReference<Activity>(activity);
    }

    ArrayList<Comida> comidas_pre_cadastradas = new ArrayList<>();
    EditText nomeComida;
    EditText preco;
    EditText quantidadePorPessoa;
    TableLayout listComidasPrecadastradas;
    Button btnCancelarComida;
    Button btnOKComida;

    protected void addComidaPrecadastradasNaTela(Comida comida, TableLayout table){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.copy);
        i.setPadding(5, 50, 5, 50);
        i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(i);

        final String nome = comida.getNome();
        final Float precol = comida.getPreco();

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomeComida.setText(nome);
                preco.setText(Float.toString(precol));
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
        b.setText(Float.toString(comida.getPreco()));
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        b.setPadding(5, 50, 5, 50);
        b.setTextColor(getResources().getColor(R.color.white));
        b.setTextSize(14);
        tr.addView(b);

        b = new TextView(this);

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
        setContentView(R.layout.cadastro_comida);
        btnCancelarComida = (Button) findViewById(R.id.btnCancelarComida);
        btnOKComida = (Button) findViewById(R.id.btnOKComida);
        nomeComida = (EditText) findViewById(R.id.txtNomeComida);
        preco = (EditText) findViewById(R.id.txtPrecoComida);
        quantidadePorPessoa = (EditText) findViewById(R.id.txtQuantidadePorPessoaComida);
        listComidasPrecadastradas = (TableLayout) findViewById(R.id.listComidasPrecadastradas);

        comidas_pre_cadastradas.add(new Comida("tacos", 3f));
        comidas_pre_cadastradas.add(new Comida("hjiu1", 20f));
        comidas_pre_cadastradas.add(new Comida("pizza", 30f));
        for(Comida comida : comidas_pre_cadastradas){
            this.addComidaPrecadastradasNaTela(comida, listComidasPrecadastradas);
        }

        btnCancelarComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(ComidasActivity.class);
            }
        });

        btnOKComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeComida.getText().length() > 3 && Numeric.isNumeric(preco.getText().toString())
                        && Numeric.isNumeric(quantidadePorPessoa.getText().toString())) {
                    try {
                        SharedPreferences preferences =
                                PreferenceManager.getDefaultSharedPreferences(CadastroComidaActivity.this);
                        JSONArray listaComidaJson;

                        listaComidaJson = new JSONArray(preferences.getString("comidas_salvas", "[]"));
                        JSONObject comidaJSON = new JSONObject();
                        comidaJSON.put("nome", nomeComida.getText().toString());
                        comidaJSON.put("preco", preco.getText().toString());
                        comidaJSON.put("quantidade_por_pessoa", quantidadePorPessoa.getText().toString());

                        listaComidaJson.put(comidaJSON);
                        SharedPreferences.Editor edit = preferences.edit();
                        edit.putString("comidas_salvas", listaComidaJson.toString());
                        edit.apply();
                        goTo(ComidasActivity.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CadastroComidaActivity.this, "Dados invalidos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
    }
}
