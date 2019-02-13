package com.mm.dell.cardmaker2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mm.dell.cardmaker2.R;

public class V_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v__main);
    }

    public void test(View view) {
        startActivity(new Intent(this, H_MainActivity.class));
    }
}
