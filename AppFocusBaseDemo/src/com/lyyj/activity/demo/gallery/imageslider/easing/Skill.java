/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.lyyj.activity.demo.gallery.imageslider.easing;

import com.lyyj.activity.demo.gallery.imageslider.easing.back.BackEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.back.BackEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.back.BackEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.bounce.BounceEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.bounce.BounceEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.bounce.BounceEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.circ.CircEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.circ.CircEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.circ.CircEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.cubic.CubicEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.cubic.CubicEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.cubic.CubicEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.elastic.ElasticEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.elastic.ElasticEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.expo.ExpoEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.expo.ExpoEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.expo.ExpoEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.linear.Linear;
import com.lyyj.activity.demo.gallery.imageslider.easing.quad.QuadEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.quad.QuadEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.quad.QuadEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.quint.QuintEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.quint.QuintEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.quint.QuintEaseOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.sine.SineEaseIn;
import com.lyyj.activity.demo.gallery.imageslider.easing.sine.SineEaseInOut;
import com.lyyj.activity.demo.gallery.imageslider.easing.sine.SineEaseOut;


public enum  Skill {

    BackEaseIn(BackEaseIn.class),
    BackEaseOut(BackEaseOut.class),
    BackEaseInOut(BackEaseInOut.class),

    BounceEaseIn(BounceEaseIn.class),
    BounceEaseOut(BounceEaseOut.class),
    BounceEaseInOut(BounceEaseInOut.class),

    CircEaseIn(CircEaseIn.class),
    CircEaseOut(CircEaseOut.class),
    CircEaseInOut(CircEaseInOut.class),

    CubicEaseIn(CubicEaseIn.class),
    CubicEaseOut(CubicEaseOut.class),
    CubicEaseInOut(CubicEaseInOut.class),

    ElasticEaseIn(ElasticEaseIn.class),
    ElasticEaseOut(ElasticEaseOut.class),

    ExpoEaseIn(ExpoEaseIn.class),
    ExpoEaseOut(ExpoEaseOut.class),
    ExpoEaseInOut(ExpoEaseInOut.class),

    QuadEaseIn(QuadEaseIn.class),
    QuadEaseOut(QuadEaseOut.class),
    QuadEaseInOut(QuadEaseInOut.class),

    QuintEaseIn(QuintEaseIn.class),
    QuintEaseOut(QuintEaseOut.class),
    QuintEaseInOut(QuintEaseInOut.class),

    SineEaseIn(SineEaseIn.class),
    SineEaseOut(SineEaseOut.class),
    SineEaseInOut(SineEaseInOut.class),

    Linear(Linear.class);


    private Class easingMethod;

    private Skill(Class clazz) {
        easingMethod = clazz;
    }

    public BaseEasingMethod getMethod(float duration) {
        try {
            return (BaseEasingMethod)easingMethod.getConstructor(float.class).newInstance(duration);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("Can not init easingMethod instance");
        }
    }
}
