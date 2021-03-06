package com.iparty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iparty.Utilities.Storage;
import com.iparty.activities.home.HomeActivity;
import com.iparty.api.AuthApi;
import com.iparty.model.Token;
import com.iparty.model.User;
import com.iparty.activities.common.BaseActivity;
import com.iparty.activities.initials.ForgetPasswordActivity;
import com.iparty.activities.initials.RegisterActivity;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static class ViewHolder {
        EditText editEmail;
        EditText editPassword;
        LoginButton facebookLoginButton;
        Button buttonLogin;
        TextView textRegister;
        TextView textForgetPassword;
        CheckBox checkRememberMe;
    }

    private ViewHolder viewHolder = new ViewHolder();
    private ProgressDialog progressDialog;
    private AuthApi authApi;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.progressDialog = new ProgressDialog(MainActivity.this);

        this.authApi = new AuthApi();

        this.viewHolder.editEmail = findViewById(R.id.edit_mail);
        this.viewHolder.editPassword = findViewById(R.id.edit_password);
        this.viewHolder.buttonLogin = findViewById(R.id.button_login);
        this.viewHolder.textRegister = findViewById(R.id.text_register);
        this.viewHolder.textForgetPassword = findViewById(R.id.text_forget_password);
        this.viewHolder.checkRememberMe = findViewById(R.id.check_remenber_me);

        this.viewHolder.buttonLogin.setOnClickListener(this);
        this.viewHolder.textRegister.setOnClickListener(this);
        this.viewHolder.textForgetPassword.setOnClickListener(this);
        this.viewHolder.checkRememberMe.setOnClickListener(this);

        // External log-ins
        this.facebookLogin();

        verifyIfIsAlreadyConnected();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_login:
                commonLogin();
                break;
            case R.id.text_register:
                goTo(RegisterActivity.class);
                break;
            case R.id.text_forget_password:
                goTo(ForgetPasswordActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void facebookLogin() {
        this.viewHolder.facebookLoginButton = findViewById(R.id.facebook_login_button);
        this.callbackManager = CallbackManager.Factory.create();
        this.viewHolder.facebookLoginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(MainActivity.this, "Login com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void verifyIfIsAlreadyConnected() {
        boolean remember = new Boolean(Storage.get(MainActivity.this, getString(R.string.storage_iparty_remember_key)));

        if (remember) {
            showProgressDialog(progressDialog);
            String tokenString = Storage.get(MainActivity.this, getString(R.string.storage_iparty_token_key));
            if (tokenString != null && !tokenString.equals("")) {
                authApi.validateToken(new Token(tokenString), new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dismissProgressDialog(progressDialog);
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            goTo(HomeActivity.class);
                        } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                            viewHolder.editEmail.setText(Storage.get(MainActivity.this, getString(R.string.storage_iparty_email_key)));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dismissProgressDialog(progressDialog);
                        serverError();
                    }
                });
            }
            dismissProgressDialog(progressDialog);
        }
    }

    private void commonLogin() {
        showProgressDialog(progressDialog);

        String email = this.viewHolder.editEmail.getText().toString();
        String password = this.viewHolder.editPassword.getText().toString();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        if (viewHolder.checkRememberMe.isChecked()) {
            Storage.set(MainActivity.this, getString(R.string.storage_iparty_remember_key), Boolean.TRUE.toString());
            Storage.set(MainActivity.this, getString(R.string.storage_iparty_email_key), user.getEmail());
        } else {
            Storage.set(MainActivity.this, getString(R.string.storage_iparty_remember_key), Boolean.FALSE.toString());
            Storage.set(MainActivity.this, getString(R.string.storage_iparty_email_key), "");
        }

        authApi.login(user, new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                dismissProgressDialog(progressDialog);
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Token token = response.body();
                    Storage.set(MainActivity.this, getString(R.string.storage_iparty_token_key), token.getToken());
                    goTo(HomeActivity.class);
                } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    Toast.makeText(MainActivity.this, R.string.login_invalid_email_password, Toast.LENGTH_LONG).show();
                } else {
                    error(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                dismissProgressDialog(progressDialog);
                serverError();
            }
        });
    }
}