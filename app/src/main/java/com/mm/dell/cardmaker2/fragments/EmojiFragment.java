package com.mm.dell.cardmaker2.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.mm.dell.cardmaker2.Adapters.MaterialSymbolAdapter;
import com.mm.dell.cardmaker2.Dialogs.ColorPicker2dialog;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.activities.H_MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EmojiFragment extends Fragment implements View.OnClickListener, ColorPicker2dialog.colorpickercallback {

    ImageView iv_opacity;
    ImageView iv_size;
    ImageView iv_addview;
    ImageView iv_deleteview;

    ImageView iv_rotate;


    private String TAG = EmojiFragment.class.getSimpleName();
    private SeekBarChange seekBarChange;

    private int Handle_SeekBar = 8989;
    private SeekBar seek;

    RecyclerView recyclerview;
    private AssetManager assetManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emoji, container, false);
        initView(view);
        assetManager = getActivity().getAssets();
        seekBarChange = (SeekBarChange) getActivity();
        try {

            LoadEmoji();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return view;
    }

    private void LoadEmoji() throws IOException {
        ArrayList<String> emojiList = new ArrayList<>();

        MaterialSymbolAdapter adapter = new MaterialSymbolAdapter(Objects.requireNonNull(getActivity()), emojiList, (SeekBarChange) getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(adapter);
        String[] list = assetManager.list("emoji");
        for (String file : list) {
            emojiList.add("emoji/" + file);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        seek = view.findViewById(R.id.seek);
        recyclerview = view.findViewById(R.id.recyclerview);

        iv_opacity = view.findViewById(R.id.iv_opacity);
        iv_opacity.setOnClickListener(this);

        iv_addview = view.findViewById(R.id.iv_addview);
        iv_addview.setOnClickListener(this);

        iv_size = view.findViewById(R.id.iv_size);
        iv_size.setOnClickListener(this);

        iv_deleteview = view.findViewById(R.id.iv_deleteview);
        iv_deleteview.setOnClickListener(this);


        iv_rotate = view.findViewById(R.id.iv_rotate);
        iv_rotate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rotate:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    seek.setMax(360);
                    Handle_SeekBar = 11;
                    seek.setOnSeekBarChangeListener(seekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }
                break;
            case R.id.iv_size:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    Handle_SeekBar = 12;
                    H_MainActivity activity = (H_MainActivity) getActivity();
                    seek.setMax(activity.width);
                    seek.setOnSeekBarChangeListener(seekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }
                break;

            case R.id.iv_opacity:
                try {
                    seek.setOnSeekBarChangeListener(null);
                    seek.setMax(100);
                    Handle_SeekBar = 13;
                    seek.setOnSeekBarChangeListener(seekBarChangeListener);
                    if (seek.getVisibility() == View.GONE) {
                        seek.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }
                break;

            case R.id.iv_deleteview:
                try {
                    seekBarChange.DeleteView();
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }
                break;
            case R.id.iv_addview:
                try {
                    H_MainActivity activity1 = (H_MainActivity) getActivity();
                    assert activity1 != null;
                    activity1.addEmojiImageview();
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }
                break;


        }

    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (Handle_SeekBar) {
                case 11:
                    seekBarChange.OnRotate(progress);
                    Log.e(TAG, "  rotate is calling ");
                    break;
                case 12:
                    seekBarChange.ImageResize(progress);
                    Log.e(TAG, "  ImageResize is calling ");
                    break;
                case 13:
                    float br = progress / 100f;
                    seekBarChange.OnBrigthness(br);
                    Log.e(TAG, "  OnBrigthness is calling ");

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

