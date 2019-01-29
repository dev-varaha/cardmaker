package com.mm.dell.cardmaker2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mm.dell.cardmaker2.Utils.ImageProcessor;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_image);
        imageView2 = findViewById(R.id.iv_image2);
        imageView.setImageResource(R.drawable.images);
    }


    public void changecolor(View view) {
        Log.e("activity ", "clicked");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Log.e("activity ", "bitmap   "+bitmap);
        int pixel = bitmap.getPixel(0,0);
        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);

        Log.e("TAG",">>   "+Color.RED);

        ImageProcessor imageProcessor = new ImageProcessor(bitmap,this);
   //     imageProcessor.getPalettes(bitmap);
        imageView2.setImageBitmap(imageProcessor.replaceColor(0,0));
//        Log.e("activity ", "bitmap  b "+b);


    }

    public void setImage(Bitmap bitmap){
        imageView2.setImageBitmap(bitmap);
    }
}
