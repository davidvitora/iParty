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
import com.iparty.model.ForgetPassword;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurício Generoso on 11/2/2018
 */
public class ChangePassword extends BaseActivity {

    private static class ViewHolder {
        EditText editPassword;
        EditText editConfirmPassword ;
        Button buttonChangePassword;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ProgressDialog progressDialog;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        this.progressDialog = new ProgressDialog(this);
        this.bundle = getIntent().getExtras();

        this.viewHolder.editPassword = findViewById(R.id.edit_password);
        this.viewHolder.editConfirmPassword = findViewById(R.id.edit_confirm_password);
        this.viewHolder.buttonChangePassword = findViewById(R.id.button_change_password);

        this.viewHolder.buttonChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_change_password:
                if (validateFields())
                saveNewPassword();
                break;
        }
    }

    private boolean validateFields() {
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

    private void saveNewPassword(){
        showProgressDialog(progressDialog);

        String strToken = bundle.getString("token");
        String strEmail = bundle.getString("email");
        int code = bundle.getInt("code");

        ForgetPassword forgetPassword =
                new ForgetPassword(
                        strToken, strEmail, code,
                        this.viewHolder.editPassword.getText().toString(),
                        this.viewHolder.editConfirmPassword.getText().toString());

        new AuthApi().changePassword(forgetPassword, new Callback<Void>() {
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
        builder.setMessage(R.string.change_password_success);
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
