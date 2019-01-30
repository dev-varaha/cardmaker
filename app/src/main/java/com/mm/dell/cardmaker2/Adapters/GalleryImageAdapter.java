package com.mm.dell.cardmaker2.Adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

import java.util.ArrayList;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.IHolder> {
    Context context;
    ArrayList<String> fileList = new ArrayList<>();
    SeekBarChange seekBarChange;

    public GalleryImageAdapter(Context context, ArrayList<String> fileList, SeekBarChange seekBarChange) {
        this.context = context;
        this.fileList = fileList;
        this.seekBarChange = seekBarChange;
    }

    @NonNull
    @Override
    public IHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottomlistraw, viewGroup, false);
        return new IHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IHolder iHolder, int i) {
        Bitmap bitmap = BitmapFactory.decodeFile(fileList.get(i));
        iHolder.imageView.setImageBitmap(bitmap);
        iHolder.imageView.setOnClickListener(v -> {
            seekBarChange.ImageBitmap(bitmap);
        });


    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class IHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public IHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
