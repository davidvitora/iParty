package com.iparty;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iparty.database.dao.UserDao;
import com.iparty.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        private EditText editUsername;
        private EditText editPassword;
        private EditText editConfirmPassword;
        private EditText editEmail;
        private Button buttonCreateAccount;
    }

    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.viewHolder.editUsername = (EditText) findViewById(R.id.edit_username);
        this.viewHolder.editPassword = (EditText) findViewById(R.id.edit_password);
        this.viewHolder.editConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password);
        this.viewHolder.editEmail = (EditText) findViewById(R.id.edit_mail);
        this.viewHolder.buttonCreateAccount = (Button) findViewById(R.id.button_create_account);

        this.viewHolder.buttonCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_create_account:
                if (validateFields()){
                    createNewAccount();
                    alertSuccess();
                }
                break;
        }
    }

    private boolean validateFields() {
        if (TextUtils.isEmpty(this.viewHolder.editUsername.getText().toString())) {
            this.viewHolder.editUsername.setError("Insira o Usuario!");
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editEmail.getText().toString())) {
            this.viewHolder.editEmail.setError("É necessario informar o email");
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editPassword.getText().toString())) {
            this.viewHolder.editPassword.setError("Insira a Senha!");
            return false;
        }
        if (TextUtils.isEmpty(this.viewHolder.editConfirmPassword.getText().toString())) {
            this.viewHolder.editConfirmPassword.setError("É necessario confirmar a senha");
            return false;
        }
        if (!validPasswords()) {
            this.viewHolder.editConfirmPassword.setError("A senha não corrreponde");
            return false;
        }
        return true;
    }

    private boolean validPasswords() {
        return this.viewHolder.editPassword.getText().toString()
                .equals(this.viewHolder.editConfirmPassword.getText().toString());
    }

    private void createNewAccount(){
        UserDao userDao = new UserDao(this);
        User user = new User();
        user.setName(this.viewHolder.editUsername.getText().toString());
        user.setEmail(this.viewHolder.editEmail.getText().toString());
        user.setPassword(this.viewHolder.editPassword.getText().toString());
        userDao.insert(user);
    }

    private void alertSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso");
        builder.setMessage("Cadastro efetuado!");
        builder.setCancelable(false);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
    }
}
