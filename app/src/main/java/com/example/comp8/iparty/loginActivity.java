package com.example.comp8.iparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    private Button login_facebook;
    private Button login;
    private EditText et_email;
    private EditText et_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_senha = (EditText) findViewById(R.id.et_password);
        login_facebook = (Button) findViewById(R.id.btn_lg_facebook);
        login = (Button) findViewById(R.id.btn_lg_login);
    }

    public void cadastro(View view) {
    }
    public void esqueceu_senha(View view) {
    }
}