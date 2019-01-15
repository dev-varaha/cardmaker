package com.mm.dell.cardmaker2.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mm.dell.cardmaker2.R;
import com.mm.dell.cardmaker2.fragments.SeekBarChange;

import java.io.File;
import java.util.ArrayList;

public class GalleryDialog extends Dialog implements View.OnClickListener {
    TextView tv_ok;
    TextView tv_cancel;
    File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
    ArrayList<String> fileList = new ArrayList<>();

    SeekBarChange seekBarChange;
    RecyclerView rv;
    ArrayList<String> list = new ArrayList<>();
    PhotosAdapter adapter;
    private Context context;

    public GalleryDialog(Context context, SeekBarChange seekBarChange) {
        super(context);
        this.seekBarChange = seekBarChange;
        this.context = context;
        Log.e("GalleryDialog", "root   >>  " + root);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.photoslayout);
        rv = findViewById(R.id.rv);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_ok = findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        adapter = new PhotosAdapter(context, list);
        LinearLayoutManager manager = new GridLayoutManager(context, 2);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        list.addAll(getFile(root));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.dismiss();
                break;
            case R.id.tv_ok:
                this.dismiss();
                break;

        }
    }

    class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ImageHolder> {
        Context context;
        ArrayList<String> arrayList = new ArrayList<>();

        public PhotosAdapter(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.imagelayout, viewGroup, false);
            return new ImageHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageHolder imageHolder, int i) {
            try {
                Log.e("onBindViewHolder", "onBindViewHolder   >>  " + arrayList.get(i));
                Bitmap bitmap = BitmapFactory.decodeFile(arrayList.get(i));
                imageHolder.imageView.setImageBitmap(bitmap);
                seekBarChange.getBitmapFromGallery(bitmap);
            } catch (Exception e) {
            }

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ImageHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.iv);
            }
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
}
