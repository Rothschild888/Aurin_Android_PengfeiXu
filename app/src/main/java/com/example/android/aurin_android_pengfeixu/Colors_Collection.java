/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by apple on 16/4/17.
 * This class is a collection of different colors
 */
public final class Colors_Collection {
    public static ArrayList<Integer> reds = new ArrayList<>();
    public static ArrayList<Integer> blues = new ArrayList<>();
    public static ArrayList<Integer> greens = new ArrayList<>();
    public static ArrayList<Integer> grays = new ArrayList<>();
    public static ArrayList<Integer> purples = new ArrayList<>();
    public static int transparency = (int) (Map_Setting.opacity * 2.55);
    static int red1 = Color.argb(transparency, 255,235,238);
    static int red2 = Color.argb(transparency, 255,205,210);
    static int red3 = Color.argb(transparency, 239,154,154);
    static int red4 = Color.argb(transparency, 239,83,80);
    static int red5 = Color.argb(transparency, 244,67,54);
    static int red6 = Color.argb(transparency, 229,57,53);
    static int red7 = Color.argb(transparency, 198,40,40);
    static int red8 = Color.argb(transparency, 183,28,28);
    static int blue1 = Color.argb(transparency, 232,234,246);
    static int blue2 = Color.argb(transparency, 197,202,233);
    static int blue3 = Color.argb(transparency, 159,168,218);
    static int blue4 = Color.argb(transparency, 121,134,203);
    static int blue5 = Color.argb(transparency, 92,107,192);
    static int blue6 = Color.argb(transparency, 63,81,181);
    static int blue7 = Color.argb(transparency, 48,63,159);
    static int blue8 = Color.argb(transparency, 26,35,126);
    static int green1 = Color.argb(transparency, 224,242,241);
    static int green2 = Color.argb(transparency, 178,223,219);
    static int green3 = Color.argb(transparency, 128,203,196);
    static int green4 = Color.argb(transparency, 77,182,172);
    static int green5 = Color.argb(transparency, 38,166,154);
    static int green6 = Color.argb(transparency, 0,150,136);
    static int green7 = Color.argb(transparency, 0,137,123);
    static int green8 = Color.argb(transparency, 0,105,92);
    static int gray1 = Color.argb(transparency, 250,250,250);
    static int gray2 = Color.argb(transparency, 245,245,245);
    static int gray3 = Color.argb(transparency, 238,238,238);
    static int gray4 = Color.argb(transparency, 224,224,224);
    static int gray5 = Color.argb(transparency, 189,189,189);
    static int gray6 = Color.argb(transparency, 158,158,158);
    static int gray7 = Color.argb(transparency, 117,117,117);
    static int gray8 = Color.argb(transparency, 97,97,97);
    static int purple1 = Color.argb(transparency, 243,229,245);
    static int purple2 = Color.argb(transparency, 225,190,231);
    static int purple3 = Color.argb(transparency, 206,147,216);
    static int purple4 = Color.argb(transparency, 186,104,200);
    static int purple5 = Color.argb(transparency, 171,71,188);
    static int purple6 = Color.argb(transparency, 156,39,176);
    static int purple7 = Color.argb(transparency, 142,36,170);
    static int purple8 = Color.argb(transparency, 123,31,162);

    static {
        reds.add(red1);
        reds.add(red2);
        reds.add(red3);
        reds.add(red4);
        reds.add(red5);
        reds.add(red6);
        reds.add(red7);
        reds.add(red8);
        blues.add(blue1);
        blues.add(blue2);
        blues.add(blue3);
        blues.add(blue4);
        blues.add(blue5);
        blues.add(blue6);
        blues.add(blue7);
        blues.add(blue8);
        greens.add(green1);
        greens.add(green2);
        greens.add(green3);
        greens.add(green4);
        greens.add(green5);
        greens.add(green6);
        greens.add(green7);
        greens.add(green8);
        grays.add(gray1);
        grays.add(gray2);
        grays.add(gray3);
        grays.add(gray4);
        grays.add(gray5);
        grays.add(gray6);
        grays.add(gray7);
        grays.add(gray8);
        purples.add(purple1);
        purples.add(purple2);
        purples.add(purple3);
        purples.add(purple4);
        purples.add(purple5);
        purples.add(purple6);
        purples.add(purple7);
        purples.add(purple8);
    }
}
