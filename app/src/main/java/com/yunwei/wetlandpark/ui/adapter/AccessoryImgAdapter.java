package com.yunwei.wetlandpark.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yunwei.wetlandpark.R;
import com.yunwei.library.utils.IDensityUtils;
import com.yunwei.library.utils.ImageLoadUrlFitter;

/**
 * @Package: com.yunwei.zaina.ui.adapter
 * @Description:附件图片适配器
 * @author: Aaron
 * @date: 2016-06-14
 * @Time: 19:00
 * @version: V1.0
 */
public class AccessoryImgAdapter extends ArrayListAdapter<String> {

    public static final String ADD_IMG_FLAG = "add:";
    private boolean isShowCloseIcon = true;

    public AccessoryImgAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.accessory_gridview_item, null);
            hodler.imageView = (ImageView) convertView.findViewById(R.id.accessory_imageview);
            hodler.closeIv = (ImageView) convertView.findViewById(R.id.close_iv);

            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(IDensityUtils.getScreenW(mContext)/4-15,IDensityUtils.getScreenW(mContext)/4-15);
            params.rightMargin=5;
            params.topMargin=5;
            hodler.imageView.setLayoutParams(params);

            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }

        if (mList.get(position).startsWith(ADD_IMG_FLAG)) {
            Glide.with(mContext).load(R.mipmap.add_btn_).into(hodler.imageView);
            hodler.closeIv.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(ImageLoadUrlFitter.fitterUrl(mList.get(position))).error(R.mipmap.main_img_defaultpic_small).into(hodler.imageView);
            hodler.closeIv.setVisibility(View.VISIBLE);
        }

        if (isShowCloseIcon && !mList.get(position).startsWith(ADD_IMG_FLAG)) {
            hodler.closeIv.setVisibility(View.VISIBLE);
        } else {
            hodler.closeIv.setVisibility(View.GONE);
        }

        hodler.closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePos(position);
                if (mList.size()==9&&!mList.contains(ADD_IMG_FLAG)) {
                    mList.add(ADD_IMG_FLAG);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHodler {
        private ImageView imageView;
        private ImageView closeIv;
    }

    public void setShowCloseIcon(boolean showCloseIcon) {
        this.isShowCloseIcon = showCloseIcon;
    }
}
