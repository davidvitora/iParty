package com.iparty;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iparty.api.AuthApi;
import com.iparty.model.User;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    private static class ViewHolder {
        private EditText editUsername;
        private EditText editPassword;
        private EditText editConfirmPassword;
        private EditText editEmail;
        private Button buttonCreateAccount;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        this.viewHolder.editUsername = findViewById(R.id.edit_username);
        this.viewHolder.editPassword = findViewById(R.id.edit_password);
        this.viewHolder.editConfirmPassword = findViewById(R.id.edit_confirm_password);
        this.viewHolder.editEmail = findViewById(R.id.edit_mail);
        this.viewHolder.buttonCreateAccount = findViewById(R.id.button_create_account);

        this.viewHolder.buttonCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_create_account:
                if (validateFields()){
                    createNewAccount();
                }
                break;
        }
    }

    private boolean validateFields() {
        if (TextUtils.isEmpty(this.viewHolder.editUsername.getText().toString())) {
            this.viewHolder.editUsername.setError(getString(R.string.edit_text_error_message_insert_user));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editEmail.getText().toString())) {
            this.viewHolder.editEmail.setError(getString(R.string.edit_text_error_message_insert_email));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editPassword.getText().toString())) {
            this.viewHolder.editPassword.setError(getString(R.string.edit_text_error_message_insert_password));
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editConfirmPassword.getText().toString())) {
            this.viewHolder.editConfirmPassword.setError(getString(R.string.edit_text_error_message_insert_confirm_password));
            return false;
        }
        if (!validPasswords()) {
            this.viewHolder.editConfirmPassword.setError(getString(R.string.edit_text_error_message_passwords_not_match));
            return false;
        }
        return true;
    }

    private boolean validPasswords() {
        return this.viewHolder.editPassword.getText().toString()
                .equals(this.viewHolder.editConfirmPassword.getText().toString());
    }

    private void createNewAccount(){
        showProgressDialog(progressDialog);

        User user = new User();
        user.setName(this.viewHolder.editUsername.getText().toString());
        user.setEmail(this.viewHolder.editEmail.getText().toString());
        user.setPassword(this.viewHolder.editPassword.getText().toString());
        user.setConfirmPassword(this.viewHolder.editConfirmPassword.getText().toString());

        new AuthApi().signup(user, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                dismissProgressDialog(progressDialog);
                if (response.code() == HttpURLConnection.HTTP_CREATED){
                    alertSuccess();
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

    private void alertSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_dialog_success);
        builder.setMessage(R.string.alert_dialog_success_message);
        builder.setCancelable(false);
        builder.setNeutralButton(R.string.alert_dialog_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
    }
}
