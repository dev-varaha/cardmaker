package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

public class OpacityDialog extends Dialog implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    TextView tv_ok;
    TextView tv_cancel;
    SeekBar seekBar;
    SeekBarChange seekBarChange;

    public OpacityDialog(Context context, SeekBarChange seekBarChange) {
        super(context);
        this.seekBarChange = seekBarChange;
    }

    public void setMax_Progress(int max, int progress) {
        seekBar.setMax(max);
        seekBar.setProgress(max);
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
        setMax_Progress(100,100);
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
        float opacity = progress/100f;
        seekBarChange.OnBrigthness(opacity);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

