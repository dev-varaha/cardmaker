package com.mm.dell.cardmaker2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;

public class TextFragmentFragment extends Fragment implements View.OnClickListener {

    ImageView iv_opacity;
    ImageView iv_size;
    ImageView iv_edittext;
    ImageView iv_deleteview;
    ImageView iv_colorpicker;
    ImageView iv_rotate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        initView(view);

        return view;

    }

    private void initView(View view) {
        iv_opacity = view.findViewById(R.id.iv_opacity);
        iv_opacity.setOnClickListener(this);
        iv_size = view.findViewById(R.id.iv_textsized);
        iv_size.setOnClickListener(this);

        iv_edittext = view.findViewById(R.id.iv_edittext);
        iv_edittext.setOnClickListener(this);

        iv_deleteview = view.findViewById(R.id.iv_deleteview);
        iv_deleteview.setOnClickListener(this);

        iv_colorpicker = view.findViewById(R.id.iv_colorpicker);
        iv_colorpicker.setOnClickListener(this);

        iv_rotate = view.findViewById(R.id.iv_rotate);
        iv_rotate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rotate:

                break;
            case R.id.iv_textsized:

                break;

            case R.id.iv_opacity:

                break;

            case R.id.iv_colorpicker:

                break;

            case R.id.iv_edittext:

                break;

            case R.id.iv_deleteview:

                break;


        }

    }
}
