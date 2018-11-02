package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iparty.R;

public class CadastroFestaActivity extends AppCompatActivity {

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
    Button btn_convidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_festa);
        nome = (EditText) findViewById(R.id.txtNome);
        selectAniversario = (ImageView) findViewById(R.id.selectAniversario);
        checkAniversario = (ImageView) findViewById(R.id.checkAniversario);

        select15Anos = (ImageView) findViewById(R.id.select15Anos);
        check15Anos = (ImageView) findViewById(R.id.check15Anos);

        selectCasamento = (ImageView) findViewById(R.id.selectCasamento);
        checkCasamento = (ImageView) findViewById(R.id.checkCasamento);

        aniversariante = (EditText) findViewById(R.id.txtAniversariante);
        noivo = (EditText) findViewById(R.id.txtNoivo);
        noiva = (EditText) findViewById(R.id.txtNoiva);
        endereco = (EditText) findViewById(R.id.txtEndereco);
        btn_convidados = (Button) findViewById(R.id.btnConvidados);

        btn_convidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroFestaActivity.this, listMain.class);
                startActivity(it);
            }
        });
    }

}
