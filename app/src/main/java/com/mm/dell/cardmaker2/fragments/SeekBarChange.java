package com.mm.dell.cardmaker2.fragments;

import android.graphics.Bitmap;

import java.util.BitSet;

public interface SeekBarChange {

    void OnRotate(int r);

    void OnWidth(int w);

    void OnHeigth(int h);

    void OnTextSizeChange(int ts);

    void OnBrigthness(float b);

    void OnColorChanges(int i, int i2, int i3);

    void getBitmapImage(Bitmap bitmap);

    void setShapeImageView(int side, int shapeType);

    void ImageResize(int size);

    void DeleteView();

    void ImageBitmap(Bitmap bitmap);

}
