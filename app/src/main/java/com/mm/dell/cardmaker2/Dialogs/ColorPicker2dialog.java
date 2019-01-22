package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class ColorPicker2dialog extends Dialog implements View.OnClickListener, ColorSeekBar.OnColorChangeListener {
    TextView tv_ok;
    TextView tv_cancel;
    colorpickercallback colorpickercallback;
    ImageView iv_preview;
    //    ImageView iv_choosefromgalery;
    ColorSeekBar colorSeekBar;


    public ColorPicker2dialog(Context context, colorpickercallback seekBarChange) {
        super(context);
        this.colorpickercallback = seekBarChange;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_color_picker);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        tv_cancel.setOnClickListener(this);
        tv_ok.setOnClickListener(this);

        colorSeekBar = findViewById(R.id.colorpiker);
        iv_preview = findViewById(R.id.iv_preview);
        iv_preview.setBackgroundColor(colorSeekBar.getColor());
        //      iv_choosefromgalery = view.findViewById(R.id.iv_choosefromgalery);
        colorSeekBar.setOnColorChangeListener(this);
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
    public void onColorChangeListener(int i, int i1, int i2) {
        colorpickercallback.getColor(i, i1, i2);
        iv_preview.setBackgroundColor(i2);
    }

   public interface colorpickercallback {
        void getColor(int i, int i1, int i2);
    }
}