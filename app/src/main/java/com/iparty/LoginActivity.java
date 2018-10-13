package com.iparty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        EditText editEmail;
        EditText editPassword;
        LoginButton facebookLoginButton;
        Button buttonLogin;
        TextView textRegister;
        TextView textForgetPassword;
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
        this.viewHolder.textForgetPassword = (TextView) findViewById(R.id.text_forget_password);

        this.viewHolder.buttonLogin.setOnClickListener(this);
        this.viewHolder.textRegister.setOnClickListener(this);

        // External log-ins
        this.facebookLogin();
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
            case R.id.text_forget_password:

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void facebookLogin() {
        this.viewHolder.facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        this.callbackManager = CallbackManager.Factory.create();
        this.viewHolder.facebookLoginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(LoginActivity.this, "Login com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() { }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}