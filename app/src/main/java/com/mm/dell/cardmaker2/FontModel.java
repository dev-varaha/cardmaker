package com.mm.dell.cardmaker2;

import android.graphics.Typeface;

public class FontModel {
String font_name;
Typeface typeface;

    public FontModel() {
    }

    public FontModel(String font_name, Typeface typeface) {
        this.font_name = font_name;
        this.typeface = typeface;
    }

    public String getFont_name() {
        return font_name;
    }

    public void setFont_name(String font_name) {
        this.font_name = font_name;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }
}
