package com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.specials.out;

import android.view.View;

import com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.BaseViewAnimator;
import com.lyyj.activity.demo.gallery.imageslider.easing.Glider;
import com.lyyj.activity.demo.gallery.imageslider.easing.Skill;
import com.nineoldandroids.animation.ObjectAnimator;

public class TakingOffAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleX", 1f, 1.5f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1f, 1.5f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "alpha", 1, 0))
        );
    }
}
