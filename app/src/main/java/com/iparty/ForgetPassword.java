package com.iparty;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    private static class ViewHolder {
        EditText editEmail;
        Button buttonSendEmail;
    }

    private ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        this.viewHolder.editEmail = findViewById(R.id.edit_forget_mail);
        this.viewHolder.buttonSendEmail = findViewById(R.id.button_send_email);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button_send_email:

                break;
        }

    }

}
