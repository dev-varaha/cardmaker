package com.mm.dell.cardmaker2.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.mm.dell.cardmaker2.Adapters.GalleryImageAdapter;
import com.mm.dell.cardmaker2.Adapters.ShapeImageAdapter;
import com.mm.dell.cardmaker2.Dialogs.ColorPicker2dialog;
import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.activities.CardMainActivity;

import java.io.File;
import java.util.ArrayList;

import static com.mm.dell.cardmaker2.Constants.IMAGE_REQUEST_CODE;

public class ShapeFragment extends Fragment implements View.OnClickListener {
    final String TAG = ShapeFragment.class.getSimpleName();
    RecyclerView recyclerview_shape;
    RecyclerView recyclerview_shape_image;
    ShapeImageAdapter shapeImageAdapter;
    File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    ArrayList<String> fileList = new ArrayList<>();
    GalleryImageAdapter adapter;
    ImageView iv_opacity;
    ImageView iv_size;
    ImageView iv_addview;
    ImageView iv_deleteview;
    ImageView iv_rotate;
    ImageView iv_back;
    ImageView iv_showshape;
    RelativeLayout rv_shape;
    HorizontalScrollView scrollutil;
    SeekBar seek;
    private int Handle_SeekBar = 8866;
    private SeekBarChange seekBarChange;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shape, container, false);
        initView(view);
        seekBarChange = (SeekBarChange) getActivity();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView(View view) {

        seek = view.findViewById(R.id.seek);

        rv_shape = view.findViewById(R.id.relative_shape);
//        rv_shape.setOnClickListener(this);
        iv_showshape = view.findViewById(R.id.iv_showshape);
        iv_showshape.setOnClickListener(this);

        scrollutil = view.findViewById(R.id.scrollutil);

        iv_opacity = view.findViewById(R.id.iv_opacity);
        iv_opacity.setOnClickListener(this);

        iv_back = view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);

        iv_addview = view.findViewById(R.id.iv_addview);
        iv_addview.setOnClickListener(this);

        iv_size = view.findViewById(R.id.iv_size);
        iv_size.setOnClickListener(this);

        iv_deleteview = view.findViewById(R.id.iv_deleteview);
        iv_deleteview.setOnClickListener(this);

        iv_rotate = view.findViewById(R.id.iv_rotate);
        iv_rotate.setOnClickListener(this);
        recyclerview_shape = view.findViewById(R.id.recyclerview_shape);
        recyclerview_shape_image = view.findViewById(R.id.recyclerview_shape_image);
        shapeImageAdapter = new ShapeImageAdapter(getActivity(), (SeekBarChange) getActivity());
        recyclerview_shape.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_shape.setAdapter(shapeImageAdapter);
        adapter = new GalleryImageAdapter(getActivity(), fileList, (SeekBarChange) getActivity());
        recyclerview_shape_image.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_shape_image.setAdapter(adapter);
        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);
        } else {
            fileList.addAll(getFile(root));
            adapter.notifyDataSetChanged();
        }
    }





    public ArrayList<String> getFile(File dir) {
        File listFile[] = dir.listFiles();
     /*   Log.e("getFile","getFile   dir.listFiles()  "+ dir.listFiles());
        Log.e("getFile","getFile  listFile  "+listFile.length);*/
        if (listFile != null && listFile.length > 0) {
            for (File file : listFile) {
                if (file.isDirectory()) {
                    getFile(file);
                    Log.e("getFile", "getFile  isDirectory  " + file);

                } else {

                    if (file.getName().endsWith(".png")
                            || file.getName().endsWith(".jpg")
                            || file.getName().endsWith(".jpeg")
                            || file.getName().endsWith(".bmp")
                            || file.getName().endsWith(".webp")) {
                        String temp = file.getPath().substring(0, file.getPath().lastIndexOf('/'));
                        /*if (!fileList.contains(temp))*/
                        fileList.add(file.getAbsolutePath());
                        Log.e("getFile", "getFile  temp  " + temp);
                    }
                }
            }
        }
        Log.e("getFile", "getFile   fileList.size() " + fileList.size());
        return fileList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_back:
                try {
                    if (scrollutil.getVisibility() == View.GONE) {
                        scrollutil.setVisibility(View.VISIBLE);
                        rv_shape.setVisibility(View.GONE);
                    }
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }
                break;

            case R.id.iv_showshape:
                try {

                    if (rv_shape.getVisibility() == View.GONE) {
                        rv_shape.setVisibility(View.VISIBLE);
                        scrollutil.setVisibility(View.GONE);
                    }
                    if (seek.getVisibility() == View.VISIBLE) {
                        seek.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                }
                break;


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
                    CardMainActivity activity = (CardMainActivity) getActivity();
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
                    CardMainActivity activity1 = (CardMainActivity) getActivity();
                    assert activity1 != null;
                    activity1.addImagetoImageview();
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

}
