package com.mm.dell.cardmaker2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mm.dell.cardmaker2.R;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

public class ColorPickerFragment extends Fragment implements ColorSeekBar.OnColorChangeListener {
    ImageView iv_preview;
    //    ImageView iv_choosefromgalery;
    ColorSeekBar colorSeekBar;
    SeekBarChange seekBarChange;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setListner(SeekBarChange seekBarChange) {
        this.seekBarChange = seekBarChange;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_picker, container, false);
     ///   initView(view);

        return view;
    }

   /* private void initView(View view) {
        colorSeekBar = view.findViewById(R.id.colorpiker);
        iv_preview = view.findViewById(R.id.iv_preview);
        //      iv_choosefromgalery = view.findViewById(R.id.iv_choosefromgalery);
        colorSeekBar.setOnColorChangeListener(this);
    *//*    iv_choosefromgalery.setOnClickListener(v -> {

        });*//*
    }
*/
    @Override
    public void onColorChangeListener(int i, int i1, int i2) {

//        Toast.makeText(getActivity(), "i " + i + " i1 " + i1 + " i2  " + i2, Toast.LENGTH_SHORT).show();
        ///       iv_preview.setColorFilter(i2);
      /*  iv_preview.setBackgroundColor(i2);
        seekBarChange.OnColorChanges(i, i1, i2);*/
    }

}
