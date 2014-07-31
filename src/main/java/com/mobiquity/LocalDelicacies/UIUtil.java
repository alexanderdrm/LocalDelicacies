package com.mobiquity.LocalDelicacies;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

/**
 * Created by dalexander on 7/31/14.
 */
public class UIUtil {
    public static void setImageDrawableCrossFade(ImageView imageView, Drawable drawable) {
        setImageDrawableCrossFade(imageView, drawable, 500);
    }

    public static void setImageDrawableCrossFade(ImageView imageView, Drawable newDrawable, int fadeTime)
    {
        Drawable currentDrawable = imageView.getDrawable();
        if(currentDrawable != null) {
            Drawable [] arrayDrawable = new Drawable[2];
            arrayDrawable[0] = currentDrawable;
            arrayDrawable[1] = newDrawable;
            TransitionDrawable transitionDrawable = new TransitionDrawable(arrayDrawable);
            transitionDrawable.setCrossFadeEnabled(true);
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(fadeTime);
        } else {
            imageView.setImageDrawable(newDrawable);
        }
    }
}
