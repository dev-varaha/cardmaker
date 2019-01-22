package com.mm.dell.cardmaker2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.mm.dell.cardmaker2.Dialogs.ColorPicker2dialog;
import com.mm.dell.cardmaker2.R;

public class SymbolsFragment extends Fragment implements View.OnClickListener, ColorPicker2dialog.colorpickercallback {
    private static final String TAG = SymbolsFragment.class.getSimpleName();
    ImageView iv_opacity;
    ImageView iv_size;
    ImageView iv_deleteview;
    ImageView iv_colorpicker;
    ImageView iv_rotate;
    SeekBar seek;
    int Handle_SeekBar = 111;
    SeekBarChange seekBarChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symbols, container, false);
        initView(view);
        seekBarChange = (SeekBarChange) getActivity();

        return view;
    }

    private void initView(View view) {
        iv_opacity = view.findViewById(R.id.iv_opacity);
        iv_opacity.setOnClickListener(this);
        iv_size = view.findViewById(R.id.iv_size);
        iv_size.setOnClickListener(this);


        iv_deleteview = view.findViewById(R.id.iv_deleteview);
        iv_deleteview.setOnClickListener(this);

        iv_colorpicker = view.findViewById(R.id.iv_colorpicker);
        iv_colorpicker.setOnClickListener(this);

        iv_rotate = view.findViewById(R.id.iv_rotate);
        seek = view.findViewById(R.id.seek);
        iv_rotate.setOnClickListener(this);
        seek.setOnSeekBarChangeListener(seekBarChangeListener);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rotate:
                seek.setMax(360);
                Handle_SeekBar = 11;
                break;
            case R.id.iv_size:

                Handle_SeekBar = 12;
                break;

            case R.id.iv_opacity:
                seek.setMax(100);
                Handle_SeekBar = 13;
                break;

            case R.id.iv_colorpicker:
                ColorPicker2dialog dialog = new ColorPicker2dialog(getActivity(), this);
                dialog.show();
                break;

            case R.id.iv_deleteview:

                break;
        }
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (Handle_SeekBar) {
                case 11:
                    seekBarChange.OnRotate(progress);
                    Log.e(TAG,"  rotate is calling ");
                    break;
                case 12:
                    seekBarChange.ImageResize(progress);
                    Log.e(TAG,"  ImageResize is calling ");

                    break;
                case 13:
                    float br = progress / 100f;
                    seekBarChange.OnBrigthness(br);
                    Log.e(TAG,"  OnBrigthness is calling ");

                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void getColor(int i, int i1, int i2) {
        seekBarChange.OnColorChanges(i, i1, i2);
    }
}
