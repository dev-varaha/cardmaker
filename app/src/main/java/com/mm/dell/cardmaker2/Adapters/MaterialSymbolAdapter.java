package com.mm.dell.cardmaker2.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MaterialSymbolAdapter extends RecyclerView.Adapter<MaterialSymbolAdapter.IHolder> {
    Context context;
    ArrayList<String> list = new ArrayList<>();
    SeekBarChange seekBarChange;

    public MaterialSymbolAdapter(Context context, ArrayList<String> list, SeekBarChange seekBarChange) {
        this.context = context;
        this.list = list;
        this.seekBarChange = seekBarChange;
        manager = context.getAssets();
    }

    @NonNull
    @Override
    public IHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.imagelayouts, viewGroup, false);
        return new IHolder(view);
    }

    AssetManager manager;

    @Override
    public void onBindViewHolder(@NonNull IHolder iHolder, int i) {
        InputStream is;
        Log.e("manager", ">>>>    iii<  .. >>  " + i);
        Bitmap bitmap = null;
        try {
            Log.e("manager", ">>>>     " + manager);
            is = manager.open(list.get(i));
            Log.e("manager", ">>>>  is   " + is);
            bitmap = BitmapFactory.decodeStream(is);
            iHolder.imageView.setImageBitmap(bitmap);
            Log.e("bitmap", ">>>>     " + bitmap);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", ">>>>   1  " + e.getMessage());

        }

        Bitmap finalBitmap = bitmap;
        iHolder.imageView.setOnClickListener(v -> {
            seekBarChange.getBitmapImage(finalBitmap);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class IHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public IHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
