package com.mm.dell.cardmaker2.Utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class ImageProcessor {
    Bitmap mImage;
    boolean mIsError = false;

    public ImageProcessor(final Bitmap image) {
        mImage = image.copy(image.getConfig(), image.isMutable());
        if (mImage == null) {
            mIsError = true;
        }
    }

    public boolean isError() {
        return mIsError;
    }

    public void setImage(final Bitmap image) {
        mImage = image.copy(image.getConfig(), image.isMutable());
        if (mImage == null) {
            mIsError = true;
        } else {
            mIsError = false;
        }
    }

    public Bitmap getImage() {
        if (mImage == null) {
            return null;
        }
        return mImage.copy(mImage.getConfig(), mImage.isMutable());
    }

    public void free() {
        if (mImage != null && !mImage.isRecycled()) {
            mImage.recycle();
            mImage = null;
        }
    }

    public Bitmap replaceColor(int fromColor, int targetColor) {
        Log.e("Imageproc", "  replace called");
        if (mImage == null) {
            return null;
        }

        int width = mImage.getWidth();
        int height = mImage.getHeight();
        int[] pixels = new int[width * height];
        mImage.getPixels(pixels, 0, width, 0, 0, width, height);
        Log.e("Imageproc", "  replace called size " + pixels.length);

        for (int x = 0; x < pixels.length; ++x) {
            if (pixels[x] == fromColor) {
                Log.e("Imageproc", "  replace called true>> ");
            }
            int r = Color.red(pixels[x]);
            int g = Color.green(pixels[x]);
            int b = Color.blue(pixels[x]);
            if ((r > 240) && (g > 240) && (b > 240)) {
                 pixels[x] = pixels[x];
            } else if ((r < 10) && (g < 10) && (b < 10)) {
                pixels[x] = pixels[x];
            } else if (((r < 200) && (r > 180)) && ((g < 200) && (g > 180)) && ((b < 200) && (b > 180))) {
                pixels[x] = Color.BLACK;
            } else if (((r < 135) && (r > 120)) && (g < 10) && (b < 10)) {
                pixels[x] = Color.BLUE;
            } else if ((r > 240) && (g > 240) && (b < 10)) {
                pixels[x] = Color.CYAN;
            } else if (((r < 130) && (r > 110)) && ((b < 130) && (g > 115)) && (b < 10)) {
                pixels[x] = Color.DKGRAY;
            } else if ((r < 30) && (g > 230) && (b < 30)) {
                pixels[x] = Color.MAGENTA;
            } else if ((r < 10) && ((g < 130) && (g > 110)) && (b < 10)) {
                pixels[x] = Color.GREEN;
            } else if ((r < 10) && (g > 240) && (b > 240)) {
                pixels[x] = Color.GREEN;
            }

        }

        Bitmap newImage = Bitmap.createBitmap(width, height, mImage.getConfig());
        newImage.setPixels(pixels, 0, width, 0, 0, width, height);

        return newImage;
    }
}