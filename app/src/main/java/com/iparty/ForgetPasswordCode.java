package com.iparty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iparty.api.AuthApi;
import com.iparty.model.ForgetPassword;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurício Generoso on 11/2/2018
 */
public class ForgetPasswordCode extends BaseActivity {

    private static class ViewHolder {
        EditText editNumber1;
        EditText editNumber2;
        EditText editNumber3;
        EditText editNumber4;
        EditText editNumber5;
        Button buttonValidateCode;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ProgressDialog progressDialog;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_code);

        this.progressDialog = new ProgressDialog(this);
        this.bundle = getIntent().getExtras();

        this.viewHolder.editNumber1 = findViewById(R.id.edit_number_1);
        this.viewHolder.editNumber2 = findViewById(R.id.edit_number_2);
        this.viewHolder.editNumber3= findViewById(R.id.edit_number_3);
        this.viewHolder.editNumber4 = findViewById(R.id.edit_number_4);
        this.viewHolder.editNumber5 = findViewById(R.id.edit_number_5);
        this.viewHolder.buttonValidateCode = findViewById(R.id.button_validate_code);

        this.viewHolder.buttonValidateCode.setOnClickListener(this);
        this.insertEventsForKeepBetweenButtons();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_validate_code:
                if (validateFields()){
                    validateCode();
                }
                break;
        }
    }


    private boolean validateFields(){
        if (TextUtils.isEmpty(this.viewHolder.editNumber1.getText().toString())){
            this.viewHolder.editNumber1.setError(getString(R.string.forget_password_code_insert_the_number));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editNumber2.getText().toString())){
            this.viewHolder.editNumber2.setError(getString(R.string.forget_password_code_insert_the_number));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editNumber3.getText().toString())){
            this.viewHolder.editNumber3.setError(getString(R.string.forget_password_code_insert_the_number));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editNumber4.getText().toString())){
            this.viewHolder.editNumber4.setError(getString(R.string.forget_password_code_insert_the_number));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editNumber5.getText().toString())){
            this.viewHolder.editNumber5.setError(getString(R.string.forget_password_code_insert_the_number));
            return false;
        }
        return true;
    }

    private void validateCode(){
        showProgressDialog(progressDialog);

        String strToken = bundle.getString("token");
        String strEmail= bundle.getString("email");

        new AuthApi().validateCode(new ForgetPassword(strToken, strEmail, this.getCode()), new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                dismissProgressDialog(progressDialog);
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Intent intent = new Intent(ForgetPasswordCode.this, ChangePassword.class);
                    intent.putExtra("token", bundle.getString("token"));
                    intent.putExtra("email", bundle.getString("email"));
                    intent.putExtra("code", getCode());
                    startActivity(intent);
                } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST){
                    Toast.makeText(ForgetPasswordCode.this, R.string.forget_password_code_invalid_code, Toast.LENGTH_LONG).show();
                } else {
                    error(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t){
                dismissProgressDialog(progressDialog);
                serverError();
            }
        });
    }

    private int getCode(){
        String number1 = this.viewHolder.editNumber1.getText().toString();
        String number2 = this.viewHolder.editNumber2.getText().toString();
        String number3 = this.viewHolder.editNumber3.getText().toString();
        String number4 = this.viewHolder.editNumber4.getText().toString();
        String number5 = this.viewHolder.editNumber5.getText().toString();
        return Integer.parseInt(number1.concat(number2).concat(number3).concat(number4).concat(number5));
    }
    
    private void insertEventsForKeepBetweenButtons(){
        this.viewHolder.editNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewHolder.editNumber2.requestFocus();
            }
        });

        this.viewHolder.editNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewHolder.editNumber3.requestFocus();
            }
        });

        this.viewHolder.editNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewHolder.editNumber4.requestFocus();
            }
        });

        this.viewHolder.editNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewHolder.editNumber5.requestFocus();
            }
        });
    }
}