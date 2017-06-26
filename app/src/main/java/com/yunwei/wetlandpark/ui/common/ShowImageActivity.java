package com.yunwei.wetlandpark.ui.common;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.library.utils.ImageLoadUrlFitter;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @Package: com.yunwei.zaina.ui.activity.facility
 * @Description:图片查看
 * @author: Aaron
 * @date: 2016-06-15
 * @Time: 09:15
 * @version: V1.0
 */
public class ShowImageActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    public static final String SHOW_IMG_INDEX = "index";
    public static final String SHOW_IMG_LIST = "img_content";

    private int indext;

    private ViewPager viewPager;

    private List<String> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_show_img);
        setSwipeEnabled(false);
        setToolbarVisibility(View.GONE);

        indext = getIntent().getIntExtra(SHOW_IMG_INDEX, 0);
        list = getIntent().getStringArrayListExtra(SHOW_IMG_LIST);

        initViewPager();
    }

    @Override
    public void findViewById() {
        super.findViewById();

        viewPager = (ViewPager) findViewById(R.id.show_img_viewPager);
    }

    private void initViewPager() {
        viewPager.setAdapter(new ShowImagePagerAdapter(list));
        viewPager.setCurrentItem(indext);
    }

    private class ShowImagePagerAdapter extends PagerAdapter {

        private List<String> list;

        public ShowImagePagerAdapter(List<String> list) {
            if (list == null) {
                this.list = new ArrayList<>();
            } else
                this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_pager_img, container, false);
            assert imageLayout != null;
            final PhotoView imageView = (PhotoView) imageLayout.findViewById(R.id.image);
            final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);

            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float v, float v1) {
                    ShowImageActivity.this.finish();
                }

                @Override
                public void onOutsidePhotoTap() {
                    ShowImageActivity.this.finish();
                }
            });
            ILog.d(TAG, "url==" + ImageLoadUrlFitter.fitterUrl(list.get(position)));
            Glide.with(ShowImageActivity.this).load(ImageLoadUrlFitter.fitterUrl(list.get(position))).error(R.mipmap.main_img_defaultpic_small).into(new GlideDrawableImageViewTarget(imageView){
                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    spinner.setVisibility(View.GONE);
                }

                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    spinner.setVisibility(View.GONE);
                }

                @Override
                public void onLoadStarted(Drawable placeholder) {
                    super.onLoadStarted(placeholder);
                    spinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void onStop() {
                    super.onStop();
                    spinner.setVisibility(View.GONE);
                }
            });
            container.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
