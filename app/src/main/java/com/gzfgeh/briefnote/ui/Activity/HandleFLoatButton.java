package com.gzfgeh.briefnote.ui.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by guzhenf on 6/26/2015.
 */
public class HandleFLoatButton {
    static int textY = 0, photoY = 0, photoX = 0, soundsX = 0, soundsY = 0, movieX = 0;
    static boolean isClicked = true;

    public static void FloatButtonClick(FloatingActionButton button, FloatingActionButton textBtn,
    FloatingActionButton photoBtn, FloatingActionButton soundsBtn, FloatingActionButton movieBtn ){

        ObjectAnimator anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;

        if (textBtn == null || photoBtn == null || soundsBtn == null || movieBtn == null)
            return;

        if (isClicked) {
            textBtn.setVisibility(View.VISIBLE);
            photoBtn.setVisibility(View.VISIBLE);
            soundsBtn.setVisibility(View.VISIBLE);
            movieBtn.setVisibility(View.VISIBLE);

            textY = button.getTop() - setFloatButtonItemPosition(4, 0, 600).y;
            photoX = setFloatButtonItemPosition(4, 1, 600).x;
            photoY = button.getTop() - setFloatButtonItemPosition(4, 1, 600).y;
            soundsX = setFloatButtonItemPosition(4, 2, 600).x;
            soundsY = button.getTop() - setFloatButtonItemPosition(4, 2, 600).y;
            movieX = setFloatButtonItemPosition(4, 3, 600).x;

            anim1 = ObjectAnimator.ofFloat(textBtn, "alpha", 0f, 1f);
            anim2 = ObjectAnimator.ofFloat(textBtn, "y", button.getTop(), textY);

            anim3 = ObjectAnimator.ofFloat(photoBtn, "alpha", 0f, 1f);
            anim4 = ObjectAnimator.ofFloat(photoBtn, "y", button.getTop(), photoY);
            anim5 = ObjectAnimator.ofFloat(photoBtn, "x", button.getLeft(), photoX);

            anim6 = ObjectAnimator.ofFloat(soundsBtn, "alpha", 0f, 1f);
            anim7 = ObjectAnimator.ofFloat(soundsBtn, "y", button.getTop(), soundsY);
            anim8 = ObjectAnimator.ofFloat(soundsBtn, "x", button.getLeft(), soundsX);

            anim9 = ObjectAnimator.ofFloat(movieBtn, "alpha", 0f, 1f);
            anim10 = ObjectAnimator.ofFloat(movieBtn, "x", button.getLeft(), movieX);
            isClicked = false;
        }else{
            anim1 = ObjectAnimator.ofFloat(textBtn, "alpha", 1f, 0f);
            anim2 = ObjectAnimator.ofFloat(textBtn, "y", textY, button.getTop());

            anim3 = ObjectAnimator.ofFloat(photoBtn, "alpha", 1f, 0f);
            anim4 = ObjectAnimator.ofFloat(photoBtn, "y", photoY, button.getTop());
            anim5 = ObjectAnimator.ofFloat(photoBtn, "x", photoX, button.getLeft());

            anim6 = ObjectAnimator.ofFloat(soundsBtn, "alpha", 1f, 0f);
            anim7 = ObjectAnimator.ofFloat(soundsBtn, "y", soundsY, button.getTop());
            anim8 = ObjectAnimator.ofFloat(soundsBtn, "x", soundsX, button.getLeft());

            anim9 = ObjectAnimator.ofFloat(movieBtn, "alpha", 1f, 0f);
            anim10 = ObjectAnimator.ofFloat(movieBtn, "x", movieX, button.getLeft());
            isClicked = true;
        }
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(150);
        animSet.setInterpolator(new LinearInterpolator());
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim3).with(anim4);
        animSet.play(anim4).with(anim5);
        animSet.play(anim5).with(anim6);
        animSet.play(anim6).with(anim7);
        animSet.play(anim7).with(anim8);
        animSet.play(anim8).with(anim9);
        animSet.play(anim9).with(anim10);
        animSet.start();
    }

    private static Point setFloatButtonItemPosition(int count, int num, float r){
        int x = (int) (r * Math.sin((Math.PI / 2 / (count - 1) * num)));
        int y = (int) (r * Math.cos((Math.PI / 2 / (count - 1) * num)));
        Point point = new Point(x, y);
        return point;
    }
}
