package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        Button buttonLogin;
        EditText editEmail;
        EditText editPassword;
        TextView textRegister;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        this.viewHolder.editEmail = (EditText) findViewById(R.id.edit_mail);
        this.viewHolder.editPassword = (EditText) findViewById(R.id.edit_password);
        this.viewHolder.buttonLogin = (Button) findViewById(R.id.button_login);
        this.viewHolder.textRegister = (TextView) findViewById(R.id.text_register);

        this.viewHolder.buttonLogin.setOnClickListener(this);
        this.viewHolder.textRegister.setOnClickListener(this);

        this.callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(LoginActivity.this, "Login com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_login:
                Intent itLogin = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(itLogin);
                break;
            case R.id.text_register:
                Intent itRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(itRegister);
                break;
        }
    }

}