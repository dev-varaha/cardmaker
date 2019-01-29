package com.mm.dell.cardmaker2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mm.dell.cardmaker2.Adapters.GalleryImageAdapter;
import com.mm.dell.cardmaker2.Adapters.ShapeImageAdapter;
import com.mm.dell.cardmaker2.R;

import java.io.File;
import java.util.ArrayList;

public class ShapeFragment extends Fragment {
    RecyclerView recyclerview_shape;
    RecyclerView recyclerview_shape_image;
    ShapeImageAdapter shapeImageAdapter;
    File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    ArrayList<String> fileList = new ArrayList<>();
    GalleryImageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shape, container, false);
        recyclerview_shape = view.findViewById(R.id.recyclerview_shape);
        recyclerview_shape_image = view.findViewById(R.id.recyclerview_shape_image);
        shapeImageAdapter = new ShapeImageAdapter(getActivity());
        recyclerview_shape.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_shape.setAdapter(shapeImageAdapter);
        adapter = new GalleryImageAdapter(getActivity(), fileList);
        recyclerview_shape_image.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_shape_image.setAdapter(adapter);
        fileList.addAll(getFile(root));
        adapter.notifyDataSetChanged();
        return view;
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

}
