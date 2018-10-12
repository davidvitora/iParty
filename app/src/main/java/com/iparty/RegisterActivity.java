package com.iparty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp8.iparty.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText email;
    private Button btnCreateAccount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        email = (EditText) findViewById(R.id.etEmail);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
    }

    boolean ValidadePassword() {
        String pass1 = etPassword.getText().toString();
        String pass2 = etConfirmPassword.getText().toString();

        if (pass1.equals(pass2)) {
            return true;
        } else {
            etConfirmPassword.setError("A senha não corrreponde");
            Toast.makeText(RegisterActivity.this, "As senhas não são iguais!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    boolean ValidadeNull() {
        if (TextUtils.isEmpty(etUsername.getText())) {
            etUsername.setError("Insira o Usuario!");
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Insira a Senha!");
            return false;
        }
        if (TextUtils.isEmpty(etConfirmPassword.getText())) {
            etConfirmPassword.setError("É necessario confirmar a senha");
            return false;
        }
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("É necessario informar o email");
            return false;
        }
        return true;
    }
}
