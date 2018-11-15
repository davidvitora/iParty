package com.iparty;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iparty.R;
import com.iparty.enums.Festa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CadastroFestaActivity extends AppCompatActivity implements View.OnClickListener {
    private static class ViewHolder {
        EditText nome;
        EditText data;

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
        RelativeLayout rlFestaAniversario;
        RelativeLayout rl15ano;
        RelativeLayout rlCasamento;

        LinearLayout rlTxtAniversariante;
        TextInputLayout rlTxtNoiva;
        LinearLayout rlTxtNoivo;
        TextInputLayout rlTxtEndereco;

    }

    private Festa currentSelectedParty = null;

    private ViewHolder viewHolder = new ViewHolder();
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_festa);
        viewHolder.nome = (EditText) findViewById(R.id.txtNome);
        viewHolder.data = (EditText) findViewById(R.id.txtData);
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

        viewHolder.rlFestaAniversario = (RelativeLayout) findViewById(R.id.rlFestaAniversario);
        viewHolder.rl15ano = (RelativeLayout) findViewById(R.id.rl15Anos);
        viewHolder.rlCasamento = (RelativeLayout) findViewById(R.id.rlCasamento);


        this.viewHolder.data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                   datePicker.show();
                }
            }
        });


        this.viewHolder.rlTxtNoiva = (TextInputLayout) findViewById(R.id.rlTxtNoiva);
        this.viewHolder.rlTxtNoivo = (LinearLayout) findViewById(R.id.rlTxtNoivo);
        this.viewHolder.rlTxtAniversariante = (LinearLayout) findViewById(R.id.rlTxtAniversariante);
        this.viewHolder.rlTxtEndereco = (TextInputLayout) findViewById(R.id.rlTxtEndereco);

        this.viewHolder.rlFestaAniversario.setOnClickListener(this);
        this.viewHolder.rl15ano.setOnClickListener(this);
        this.viewHolder.rlCasamento.setOnClickListener(this);
        this.viewHolder.data.setOnClickListener(this);


        this.viewHolder.rlTxtEndereco.setVisibility(View.VISIBLE);

        datePicker = new DatePickerDialog(CadastroFestaActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    myCalendar.set(Calendar.DAY_OF_MONTH, datePicker.getDatePicker().getDayOfMonth());
                    myCalendar.set(Calendar.MONTH, datePicker.getDatePicker().getMonth());
                    myCalendar.set(Calendar.YEAR, datePicker.getDatePicker().getYear());
                    System.out.println(myCalendar.toString());
                    System.out.println(myCalendar);
                    updateLabel();
                    System.out.println("minha rola");
                    viewHolder.endereco.requestFocus();
                }
            }
        });
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.txtNoiva:
                break;
            case R.id.rlFestaAniversario:
                setGone();
                this.currentSelectedParty = Festa.ANIVERSARIO;
                viewHolder.checkAniversario.setVisibility(View.VISIBLE);
                viewHolder.rlTxtAniversariante.setVisibility(View.VISIBLE);
                break;
            case R.id.rl15Anos:
                setGone();
                this.currentSelectedParty = Festa.QUINZEANOS;
                viewHolder.check15Anos.setVisibility(View.VISIBLE);
                viewHolder.rlTxtAniversariante.setVisibility(View.VISIBLE);
                break;
            case R.id.rlCasamento:
                setGone();
                this.currentSelectedParty = Festa.CASAMENTO;
                viewHolder.checkCasamento.setVisibility(View.VISIBLE);
                viewHolder.rlTxtNoiva.setVisibility(View.VISIBLE);
                viewHolder.rlTxtNoivo.setVisibility(View.VISIBLE);
                break;
            case R.id.btnConvidados:
                break;
            case R.id.btnBebidas:
                break;
            case R.id.btnComidas:
                break;
            case R.id.btnCriarNovoEvento:
                break;
            case R.id.txtData:
                this.datePicker.show();
                break;
        }
    }

    private void setGone() {
        this.currentSelectedParty = null;
        viewHolder.checkAniversario.setVisibility(View.INVISIBLE);
        viewHolder.check15Anos.setVisibility(View.INVISIBLE);
        viewHolder.checkCasamento.setVisibility(View.INVISIBLE);

        viewHolder.rlTxtNoiva.setVisibility(View.GONE);
        viewHolder.rlTxtNoivo.setVisibility(View.GONE);
        viewHolder.rlTxtAniversariante.setVisibility(View.GONE);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
        this.viewHolder.data.setText(sdf.format(myCalendar.getTime()));
    }
}
