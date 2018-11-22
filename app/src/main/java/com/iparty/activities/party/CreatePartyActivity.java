package com.iparty.activities.party;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iparty.BebidasActivity;
import com.iparty.GuestActivity;
import com.iparty.R;
import com.iparty.enums.Party;
import com.iparty.activities.common.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreatePartyActivity extends BaseActivity {

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

        Button btnBebidas;
        Button btnConvidados;
    }

    private Party currentSelectedParty = null;
    private ViewHolder viewHolder = new ViewHolder();
    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);
        viewHolder.nome = findViewById(R.id.txtNome);
        viewHolder.data = findViewById(R.id.txtData);
        viewHolder.selectAniversario = findViewById(R.id.selectAniversario);
        viewHolder.checkAniversario = findViewById(R.id.checkAniversario);

        viewHolder.select15Anos = findViewById(R.id.select15Anos);
        viewHolder.check15Anos = findViewById(R.id.check15Anos);
        viewHolder.selectCasamento = findViewById(R.id.selectCasamento);
        viewHolder.checkCasamento = findViewById(R.id.checkCasamento);
        viewHolder.aniversariante = findViewById(R.id.txtAniversariante);
        viewHolder.noivo = findViewById(R.id.txtNoivo);
        viewHolder.noiva = findViewById(R.id.txtNoiva);
        viewHolder.endereco = findViewById(R.id.txtEndereco);

        viewHolder.rlFestaAniversario = findViewById(R.id.rlFestaAniversario);
        viewHolder.rl15ano = findViewById(R.id.rl15Anos);
        viewHolder.rlCasamento = findViewById(R.id.rlCasamento);
        viewHolder.btnConvidados = findViewById(R.id.btnConvidados);


        this.viewHolder.data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                   datePicker.show();
                }
            }
        });

        this.viewHolder.rlTxtNoiva = findViewById(R.id.rlTxtNoiva);
        this.viewHolder.rlTxtNoivo = findViewById(R.id.rlTxtNoivo);
        this.viewHolder.rlTxtAniversariante = findViewById(R.id.rlTxtAniversariante);
        this.viewHolder.rlTxtEndereco = findViewById(R.id.rlTxtEndereco);
        this.viewHolder.btnBebidas = findViewById(R.id.btnBebidas);

        this.viewHolder.rlFestaAniversario.setOnClickListener(this);
        this.viewHolder.rl15ano.setOnClickListener(this);
        this.viewHolder.rlCasamento.setOnClickListener(this);
        this.viewHolder.data.setOnClickListener(this);
        this.viewHolder.btnBebidas.setOnClickListener(this);
        this.viewHolder.btnConvidados.setOnClickListener(this);

        this.viewHolder.rlTxtEndereco.setVisibility(View.VISIBLE);

        datePicker = new DatePickerDialog(CreatePartyActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
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
                this.currentSelectedParty = Party.ANIVERSARIO;
                viewHolder.checkAniversario.setVisibility(View.VISIBLE);
                viewHolder.rlTxtAniversariante.setVisibility(View.VISIBLE);
                break;
            case R.id.rl15Anos:
                setGone();
                this.currentSelectedParty = Party.QUINZEANOS;
                viewHolder.check15Anos.setVisibility(View.VISIBLE);
                viewHolder.rlTxtAniversariante.setVisibility(View.VISIBLE);
                break;
            case R.id.rlCasamento:
                setGone();
                this.currentSelectedParty = Party.CASAMENTO;
                viewHolder.checkCasamento.setVisibility(View.VISIBLE);
                viewHolder.rlTxtNoiva.setVisibility(View.VISIBLE);
                viewHolder.rlTxtNoivo.setVisibility(View.VISIBLE);
                break;
            case R.id.btnConvidados:
                goTo(GuestActivity.class);
                break;
            case R.id.btnBebidas:
                goTo(BebidasActivity.class);
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
