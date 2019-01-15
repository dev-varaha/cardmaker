package com.mm.dell.cardmaker2;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.provider.FontsContract;
import android.view.View;

public interface RecyclerOnItemClickListner {

    void OnItemClickLister( int position, Typeface typeface);

    void getLayerOneImage(Bitmap bitmap);
}
