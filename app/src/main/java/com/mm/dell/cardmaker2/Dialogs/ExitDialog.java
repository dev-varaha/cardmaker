package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

import java.io.File;
import java.util.ArrayList;

public class ExitDialog extends Dialog implements View.OnClickListener {
    TextView tv_ok;
    TextView tv_cancel;
    private Context context;
    private AppCompatActivity appCompatActivity;

    public ExitDialog(Context context, AppCompatActivity appCompatActivity) {
        super(context);
        this.context = context;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exitdialog);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.dismiss();
                break;
            case R.id.tv_ok:
                try {
                    appCompatActivity.finish();
                    this.dismiss();
                } catch (Exception e) {
                }
                break;

        }
    }
}
