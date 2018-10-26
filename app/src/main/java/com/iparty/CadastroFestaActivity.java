package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iparty.R;

public class CadastroFestaActivity extends AppCompatActivity implements View.OnClickListener {
    private static class ViewHolder {
        EditText nome;

        ImageView selectAniversario;
        ImageView checkAniversario;

        ImageView select15Anos;
        ImageView check15Anos;

        ImageView selectCasamento;
        ImageView checkCasamento;

        EditText aniversariante;
        EditText noivo;
        EditText noiva;
        EditText endereco;
        RelativeLayout rl15ano;


    }

    private ViewHolder viewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_festa);
        viewHolder.nome = (EditText) findViewById(R.id.txtNome);
        viewHolder.selectAniversario = (ImageView) findViewById(R.id.selectAniversario);
        viewHolder.checkAniversario = (ImageView) findViewById(R.id.checkAniversario);

        viewHolder.select15Anos = (ImageView) findViewById(R.id.select15Anos);
        viewHolder.check15Anos = (ImageView) findViewById(R.id.check15Anos);
        viewHolder.selectCasamento = (ImageView) findViewById(R.id.selectCasamento);
        viewHolder.checkCasamento = (ImageView) findViewById(R.id.checkCasamento);
        viewHolder.aniversariante = (EditText) findViewById(R.id.txtAniversariante);
        viewHolder.noivo = (EditText) findViewById(R.id.txtNoivo);
        viewHolder.noiva = (EditText) findViewById(R.id.txtNoiva);
        viewHolder.endereco = (EditText) findViewById(R.id.txtEndereco);
        viewHolder.rl15ano = (RelativeLayout) findViewById(R.id.rl15Anos);

        this.viewHolder.checkAniversario.setOnClickListener(this);
        this.viewHolder.rl15ano.setOnClickListener(this);
        this.viewHolder.checkCasamento.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.txtNoiva:
                break;
            case R.id.rlFestaAniversario:
                setGone();
                viewHolder.checkAniversario.setVisibility(View.VISIBLE);
                break;
            case R.id.rl15Anos:
                setGone();
                viewHolder.check15Anos.setVisibility(View.VISIBLE);
                break;
            case R.id.rlCasamento:
                setGone();
                viewHolder.checkCasamento.setVisibility(View.VISIBLE);
                break;
            case R.id.text_forget_password:
                break;
        }
    }
    private void setGone() {
        viewHolder.checkAniversario.setVisibility(View.INVISIBLE);
        viewHolder.check15Anos.setVisibility(View.INVISIBLE);
        viewHolder.checkCasamento.setVisibility(View.INVISIBLE);
    }
}
