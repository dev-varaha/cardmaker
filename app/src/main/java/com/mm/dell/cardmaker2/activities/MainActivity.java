package com.mm.dell.cardmaker2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mm.dell.cardmaker2.Dialogs.ExitDialog;
import com.mm.dell.cardmaker2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Post_V(View view) {
        startActivity(new Intent(this, V_MainActivity.class));

    }

    public void Post_H(View view) {
        startActivity(new Intent(this, H_MainActivity.class));

    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog(this, this);
        exitDialog.show();
    }
}
