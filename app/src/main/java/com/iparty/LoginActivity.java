package com.iparty;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iparty.Utilities.Storage;
import com.iparty.api.AuthApi;
import com.iparty.model.Token;
import com.iparty.model.User;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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
        this.setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getString(R.string.progress_dialog_message));
        progressDialog.setCancelable(false);

        this.viewHolder.editEmail = findViewById(R.id.edit_mail);
        this.viewHolder.editPassword = findViewById(R.id.edit_password);
        this.viewHolder.buttonLogin = findViewById(R.id.button_login);
        this.viewHolder.textRegister = findViewById(R.id.text_register);
        this.viewHolder.textForgetPassword = findViewById(R.id.text_forget_password);
        this.viewHolder.checkRememberMe = findViewById(R.id.check_remenber_me);

        this.viewHolder.buttonLogin.setOnClickListener(this);
        this.viewHolder.textRegister.setOnClickListener(this);
        this.viewHolder.checkRememberMe.setOnClickListener(this);

        // External log-ins
        this.facebookLogin();

        this.authApi = AuthApi.RETROFIT.create(AuthApi.class);
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
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void verifyIfIsAlreadyConnected() {
        boolean remember = new Boolean(Storage.get(LoginActivity.this, getString(R.string.storage_iparty_remember_key)));

        if (remember) {
            progressDialog.show();
            String tokenString = Storage.get(LoginActivity.this, getString(R.string.storage_iparty_token_key));
            if (tokenString != null && !tokenString.equals("")) {
                Token token = new Token(tokenString);
                Call<Void> call = authApi.validateToken(token);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                            if (response.code() == HttpURLConnection.HTTP_OK) {
                                openHomeActivity();
                            } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                                viewHolder.editEmail.setText(Storage.get(LoginActivity.this, getString(R.string.storage_iparty_email_key)));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }

    private void commonLogin() {
        progressDialog.show();

        String email = this.viewHolder.editEmail.getText().toString();
        String password = this.viewHolder.editPassword.getText().toString();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        if (viewHolder.checkRememberMe.isChecked()) {
            Storage.set(LoginActivity.this, getString(R.string.storage_iparty_remember_key), Boolean.TRUE.toString());
            Storage.set(LoginActivity.this, getString(R.string.storage_iparty_email_key), user.getEmail());
        } else {
            Storage.set(LoginActivity.this, getString(R.string.storage_iparty_remember_key), Boolean.FALSE.toString());
            Storage.set(LoginActivity.this, getString(R.string.storage_iparty_email_key), "");
        }

        Call<Token> call = authApi.login(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        Token token = response.body();
                        Storage.set(LoginActivity.this, getString(R.string.storage_iparty_token_key), token.getToken());
                        openHomeActivity();
                    } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        Toast.makeText(LoginActivity.this, R.string.login_invalid_email_password, Toast.LENGTH_LONG).show();
                    } else {
                        error(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(LoginActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openHomeActivity() {
        Intent itLogin = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(itLogin);
    }

    private void error(String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.server_error_title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNeutralButton(R.string.alert_dialog_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}