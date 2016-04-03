package com.example.joanna.fin;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by jerry on 03/04/16.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
