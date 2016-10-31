package com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.specials.in;

import android.view.View;

import com.lyyj.activity.demo.gallery.imageslider.androidanimations.library.BaseViewAnimator;
import com.lyyj.activity.demo.gallery.imageslider.easing.Glider;
import com.lyyj.activity.demo.gallery.imageslider.easing.Skill;
import com.nineoldandroids.animation.ObjectAnimator;

public class LandingAnimator extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleX", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "scaleY", 1.5f, 1f)),
                Glider.glide(Skill.QuintEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "alpha", 0, 1f))
        );
    }
}
