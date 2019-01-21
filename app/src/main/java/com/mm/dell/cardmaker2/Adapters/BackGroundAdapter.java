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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.RecyclerOnItemClickListner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BackGroundAdapter extends RecyclerView.Adapter<BackGroundAdapter.MyHolder> {
    Context context;
    ArrayList<String> backgroundlist = new ArrayList<>();
    RecyclerOnItemClickListner listner;
    AssetManager manager;

    public BackGroundAdapter(ArrayList<String> backgroundlist, Context context, RecyclerOnItemClickListner listner) {
        this.context = context;
        this.backgroundlist = backgroundlist;
        this.listner = listner;
        manager = context.getAssets();

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottomlistraw, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.textView.setVisibility(View.GONE);
        InputStream is;
        Log.e("manager", ">>>>    iii<  .. >>  " + i);
        Bitmap bitmap = null;
        try {
            Log.e("manager", ">>>>     " + manager);
            is = manager.open( backgroundlist.get(i));
            Log.e("manager", ">>>>  is   " + is);
             bitmap = BitmapFactory.decodeStream(is);
            myHolder.imageView.setImageBitmap(bitmap);
            Log.e("bitmap", ">>>>     " + bitmap);
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException", ">>>>   1  " + e.getMessage());

        }

        Bitmap finalBitmap = bitmap;
        myHolder.layout.setOnClickListener(v -> {
            listner.getLayerOneImage(finalBitmap);
        });
    }

    @Override
    public int getItemCount() {
        Log.e("SIZE", ">>>>     " + backgroundlist.size());
        return backgroundlist.size();

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        RelativeLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.root);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}

