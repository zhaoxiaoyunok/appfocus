package com.lyyj.activity.demo.imageview.autoresize;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.appfocusbase.R;
import com.lyyj.activity.demo.imageview.autoresize.MasaccioImageView.MasaccioFaceDetector;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;


import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;


public class MasaccioAutoResizeDemoActivity extends  Activity {

    private MasaccioImageView mMasaccioImageView;

    private ImageView mPreviewImageView;

   // private ProgressBar mProgressBar;

    private int imageIndex;

    private int[] imageDrawable = {R.drawable.lyyj_iv_autoresize_img1, R.drawable.lyyj_iv_autoresize_img2, R.drawable.lyyj_iv_autoresize_img3, R.drawable.lyyj_iv_autoresize_img4};

    private static DisplayImageOptions getProcessorDisplayImageOptions(
            final BitmapProcessor processor) {

        final DisplayImageOptions.Builder defaultOptionsBuilder = new DisplayImageOptions.Builder();

        return defaultOptionsBuilder.imageScaleType(ImageScaleType.NONE)
                .postProcessor(processor)
                .build();
    }

    private static ImageLoaderConfiguration getStandardOptions(final Context context,
                                                               final BitmapProcessor processor) {

        final ImageLoaderConfiguration.Builder config =
                new ImageLoaderConfiguration.Builder(context);

        config.defaultDisplayImageOptions(getProcessorDisplayImageOptions(processor));

        config.memoryCache(new WeakMemoryCache());
        config.taskExecutor(Executors.newCachedThreadPool());
        config.threadPriority(Thread.MIN_PRIORITY);
        config.imageDownloader(new IonImageDownloader(context));
        config.writeDebugLogs();

        return config.build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_iv_autoresize_activity_layout);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

     //   mProgressBar = (ProgressBar) findViewById(R.id.progress_spinner);
        mPreviewImageView = (ImageView) findViewById(R.id.preview_image);
        mMasaccioImageView = (MasaccioImageView) findViewById(R.id.masaccio_view);

        imageIndex = 0;

        findViewById(R.id.download_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                nextImage();
            }
        });

        findViewById(R.id.rotate_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                onRotate();
            }
        });

        // register the processor so to make face detection happen in the background loading thread
        ImageLoader.getInstance()
                .init(getStandardOptions(this, new FaceDetectionProcessor(
                		 MasaccioImageView.getFaceDetector())));

        nextImage();
    }

    private void nextImage() {

        ImageLoader.getInstance().cancelDisplayTask(mMasaccioImageView);

        int resId = imageDrawable[imageIndex];

        String imageUri = "drawable://" + resId;

        ImageLoader.getInstance()
                .displayImage(imageUri,
                        mMasaccioImageView, new MyImageLoadingListener(this));

        imageIndex++;

        if (imageIndex == 4) {
            imageIndex = 0;
        }
    }

    private void onRotate() {

        final MasaccioImageView masaccioImageView = mMasaccioImageView;
        final int width = masaccioImageView.getWidth();
        final int height = masaccioImageView.getHeight();

        // swap width and height
        final LayoutParams layoutParams = masaccioImageView.getLayoutParams();
        layoutParams.width = height;
        layoutParams.height = width;
        masaccioImageView.setLayoutParams(layoutParams);
    }

    private static class FaceDetectionProcessor implements BitmapProcessor {

        private final MasaccioFaceDetector mDetector;

        public FaceDetectionProcessor(final MasaccioFaceDetector detector) {

            mDetector = detector;
        }

        @Override
        public Bitmap process(final Bitmap bitmap) {

            mDetector.process(bitmap);

            return bitmap;
        }
    }

 

    private static class MyImageLoadingListener implements ImageLoadingListener {

        private WeakReference<MasaccioAutoResizeDemoActivity> mActivityReference;

        private MyImageLoadingListener(final MasaccioAutoResizeDemoActivity demoActivity) {

            mActivityReference = new WeakReference<MasaccioAutoResizeDemoActivity>(demoActivity);
        }

        @Override
        public void onLoadingStarted(final String imageUri, final View view) {

            final MasaccioAutoResizeDemoActivity demoActivity = mActivityReference.get();

            if (demoActivity != null) {

              //  demoActivity.mProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onLoadingFailed(final String imageUri, final View view,
                                    final FailReason failReason) {

            final MasaccioAutoResizeDemoActivity demoActivity = mActivityReference.get();

            if (demoActivity != null) {

                Toast.makeText(demoActivity, "Error during image loading", Toast.LENGTH_SHORT)
                        .show();
               // demoActivity.mProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onLoadingComplete(final String imageUri, final View view,
                                      final Bitmap loadedImage) {

            final MasaccioAutoResizeDemoActivity demoActivity = mActivityReference.get();

            if (demoActivity != null) {

                demoActivity.mPreviewImageView.setImageBitmap(loadedImage);
               // demoActivity.mProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onLoadingCancelled(final String imageUri, final View view) {

        }
    }
}
