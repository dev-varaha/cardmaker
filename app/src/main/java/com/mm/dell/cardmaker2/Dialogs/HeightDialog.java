package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

public class HeightDialog extends Dialog implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    TextView tv_ok;
    TextView tv_cancel;
    SeekBar seekBar;
    SeekBarChange seekBarChange;

    public HeightDialog(Context context, SeekBarChange seekBarChange) {
        super(context);
        this.seekBarChange = seekBarChange;
    }

    public void setMax_Progress(int max, int progress) {
        seekBar.setMax(max);
        seekBar.setProgress(10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.seekbardialog);
        seekBar = findViewById(R.id.seek);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        seekBar.setOnSeekBarChangeListener(this);
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
                this.dismiss();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBarChange.OnHeigth(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
