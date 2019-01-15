package com.mm.dell.cardmaker2.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.mm.dell.cardmaker2.Main2Activity;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.VerticalSeekBar;


public class ResizeRotateOpacityFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private static ResizeRotateOpacityFragment resizeRotateOpacityFragment;
    VerticalSeekBar seekBar_width;
    VerticalSeekBar seekBar_heigth;
    VerticalSeekBar seekBar_roatate;
    VerticalSeekBar seekBar_brthnss;
    SeekBarChange seekBarChange;

    public static ResizeRotateOpacityFragment resizeRotateOpacityFragment() {
        // Required empty public constructor
        return resizeRotateOpacityFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // resizeRotateOpacityFragment = this;
    }

    public void setListner(SeekBarChange seekBarChange) {
        this.seekBarChange = seekBarChange;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resize_rotate_opacity, container, false);
       // initView(view);

        return view;
    }

    private void initView(View view) {
        seekBar_brthnss = view.findViewById(R.id.seek_brigthness);
        seekBar_width = view.findViewById(R.id.seek_width);
        seekBar_heigth = view.findViewById(R.id.seek_height);
        seekBar_roatate = view.findViewById(R.id.seek_rotate);
        seekBar_roatate.setOnSeekBarChangeListener(this);
        seekBar_width.setOnSeekBarChangeListener(this);
        seekBar_heigth.setOnSeekBarChangeListener(this);
        seekBar_brthnss.setOnSeekBarChangeListener(this);
        setMax();
    }

    private void setMax() {
       /* new Handler().postDelayed(() -> {
            seekBar_heigth.setMax(Main2Activity.height);
            seekBar_width.setMax(Main2Activity.width);
            if (Main2Activity.height == 0) {
                setMax();
            } else if (Main2Activity.width == 0) {
                setMax();
            }
        }, 1000);*/
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("onProgressChanged","onProgressChanged    "+seekBar);

       /* switch (seekBar.getId()) {
            case R.id.seek_brigthness:
                float bright = progress / 100f;
                seekBarChange.OnBrigthness(bright);

                break;
            case R.id.seek_width:
                seekBarChange.OnWidth(progress);
                break;

            case R.id.seek_height:
                seekBarChange.OnHeigth(progress);

                break;

            case R.id.seek_rotate:
                seekBarChange.OnRotate(progress);

                break;


        }*/
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
