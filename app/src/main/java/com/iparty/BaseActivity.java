package com.iparty;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Maurício Generoso on 11/2/2018
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected void showProgressDialog(ProgressDialog progressDialog){
        progressDialog.setMessage(getString(R.string.progress_dialog_message));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected boolean dismissProgressDialog(ProgressDialog progressDialog){
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            return true;
        }
        return false;
    }

    protected void goTo(Class<?> toActivity) {
        Intent intent = new Intent(this, toActivity);
        startActivity(intent);
    }

    protected void cleanAndGoTo(Class<?> toActivity) {
        Intent intent = new Intent(this, toActivity);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
    }

    protected void serverError(){
        Toast.makeText(this, R.string.server_error, Toast.LENGTH_LONG).show();
    }

    protected void error(String message){
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
