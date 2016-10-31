package com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.specials.in;

import android.view.View;

import com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.BaseViewAnimator;
import com.lyyj.activity.demo.gallery.imageslider.easing.Glider;
import com.lyyj.activity.demo.gallery.imageslider.easing.Skill;
import com.nineoldandroids.animation.ObjectAnimator;

public class DropOutAnimator extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 1),
                Glider.glide(Skill.BounceEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "translationY", -distance, 0))
        );
    }
}
