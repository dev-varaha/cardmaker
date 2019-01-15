package com.mm.dell.cardmaker2;

public class Nev1_Item {
    String item_name;
    int drawbleres;

    public Nev1_Item() {
    }

    public Nev1_Item(String item_name, int drawbleres) {
        this.item_name = item_name;
        this.drawbleres = drawbleres;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getDrawbleres() {
        return drawbleres;
    }

    public void setDrawbleres(int drawbleres) {
        this.drawbleres = drawbleres;
    }
}
