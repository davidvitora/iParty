package com.iparty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iparty.api.AuthApi;
import com.iparty.model.Token;
import com.iparty.model.User;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurício Generoso on 10/18/2018
 */
public class ForgetPassword extends BaseActivity {

    private static class ViewHolder {
        EditText editEmail;
        Button buttonSendEmail;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        this.progressDialog = new ProgressDialog(this);

        this.viewHolder.editEmail = findViewById(R.id.edit_forget_mail);
        this.viewHolder.buttonSendEmail = findViewById(R.id.button_send_email);

        this.viewHolder.buttonSendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_send_email:
                if (validateFields()){
                    sendEmail();
                }
                break;
        }
    }

    private boolean validateFields(){
        if (TextUtils.isEmpty(this.viewHolder.editEmail.getText().toString())) {
            this.viewHolder.editEmail.setError(getString(R.string.edit_text_error_message_insert_email));
            return false;
        }
        return true;
    }

    private void sendEmail(){
        showProgressDialog(progressDialog);

        User user = new User();
        user.setEmail(this.viewHolder.editEmail.getText().toString());

        new AuthApi().forgetPassword(user, new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response){
                dismissProgressDialog(progressDialog);
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Intent intent = new Intent(ForgetPassword.this, ForgetPasswordCode.class);
                    intent.putExtra("token", response.body().getToken());
                    intent.putExtra("email", viewHolder.editEmail.getText().toString());
                    startActivity(intent);
                } else if (response.code() == HttpURLConnection.HTTP_NOT_FOUND){
                    Toast.makeText(ForgetPassword.this, R.string.forget_password_email_not_found, Toast.LENGTH_LONG).show();
                } else {
                    error(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t){
                dismissProgressDialog(progressDialog);
                serverError();
            }
        });
    }
}
