package com.example.comp8.iparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private EditText confirm_password;
    private EditText email;
    private Button confirm_register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPassword);
        confirm_password = (EditText) findViewById(R.id.editTextConfirmPassword);
        email = (EditText) findViewById(R.id.editTextEmail);
        confirm_register = (Button) findViewById(R.id.buttonCreateAccount);


    }

    boolean ValidadePassword() {
        String pass1 = password.getText().toString();
        String pass2 = confirm_password.getText().toString();

        if (pass1.equals(pass2)) {
            return true;
        } else {
             confirm_password.setError("A senha não corrreponde");
            Toast.makeText(RegisterActivity.this, "As senhas não são iguais!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    boolean ValidadeNull() {
        if  (TextUtils.isEmpty(login.getText())){
            login.setError("Insira o Usuario!");
            return false;
        }
        if (TextUtils.isEmpty(password.getText()))
        {
            password.setError("Insira a Senha!");
            return false;
        }
        if (TextUtils.isEmpty(confirm_password.getText())){
            confirm_password.setError("É necessario confirmar a senha");
            return false;
        }
        if (TextUtils.isEmpty(email.getText())){
            email.setError("É necessario informar o email");
            return false;
        }
        return true;
    }
}
