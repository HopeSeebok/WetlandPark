package com.yunwei.wetlandpark.ui.common;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.ImageBean;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.view.PhotoAlbumImageView;
import com.yunwei.library.utils.ImageLoadUrlFitter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Package: com.yunwei.zaina.ui.activity.facility
 * @Description:图片选择
 * @author: Aaron
 * @date: 2016-06-14
 * @Time: 16:54
 * @version: V1.0
 */
public class ShowPhotoAlbumActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    public final static String SELETE_COUNT = "count";
    public final static int ALBUM = 4322;

    private GridView mGridView;

    private TextView textView;

    private Button complteBtn;

    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();// 用来存放文件名和图片的路径

    private List<ImageBean> pathList = new ArrayList<ImageBean>();

    /**
     * 用来存储图片的选中情况
     */
    private HashMap<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();

    private Dialog dialog;

    private int count = 0;

    private int seleteCount = 1;


    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case Constants.REFRESH_SUCCESS:
                DialogFactory.dimissDialog(dialog);
                mGridView.setAdapter(new AlbumAdapter());
                break;
            case Constants.REFRESH_SUCCESS2:
                textView.setText("已选择[" + mSelectMap.size() + "]张");
                break;

            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_photo_album);
        setToolbarTitle("图片选择");
        seleteCount = getIntent().getExtras().getInt(SELETE_COUNT);
        initTitle();
        getImages();
    }

    @Override
    public void findViewById() {
        super.findViewById();
        mGridView = (GridView) findViewById(R.id.photo_album_gridview);
        textView = (TextView) findViewById(R.id.album_selete_text);
        complteBtn = (Button) findViewById(R.id.album_complte_btn);
    }

    private void initTitle() {
        complteBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mSelectMap.size() <= 0) {
                    showToast("请选择图片");
                    return;
                }
                List<String> images = new ArrayList<String>();
                for (int i = 0; i < pathList.size(); i++) {
                    if (pathList.get(i).isCheck()) {
                        images.add(pathList.get(i).getPath());
                    }
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra("list", (ArrayList<String>) images);
                setResult(ALBUM, intent);
                ShowPhotoAlbumActivity.this.finish();
            }
        });
    }

    private void getImages() {
        if (!IFileUtils.checkSDcard()) {
            return;
        }
        dialog = DialogFactory.createLoadingDialog(this, "获取图片...");
        new Thread(new Runnable() {

            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ShowPhotoAlbumActivity.this
                        .getContentResolver();
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_ADDED);
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    // mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.))

                    // 获取该图片的父路径名
                    String parentName = new File(path).getParentFile()
                            .getName();
                    if ("Camera".equals(parentName) || "img".equals(parentName)
                            || "Screenshots".equals(parentName)
                            || "activity".equals(parentName)
                            || "APUS_Wallpaper".equals(parentName)) {
                        ImageBean bean = new ImageBean();
                        bean.setPath(path);
                        bean.setCheck(false);
                        if ("Camera".equals(parentName)) {
                            pathList.add(0, bean);
                        } else {
                            pathList.add(bean);
                        }
                    }
                    ILog.i("whz", "name=" + parentName);

                    // 根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        mGruopMap.get(parentName).add(path);
                    }
                }
                mCursor.close();
                mHandler.sendEmptyMessage(Constants.REFRESH_SUCCESS);
            }
        }).start();
    }

    public class AlbumAdapter extends BaseAdapter {

        private Point mPoint = new Point(0, 0);// 用来封装ImageView的宽和高的对象


        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public Object getItem(int position) {
            return pathList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = LayoutInflater.from(ShowPhotoAlbumActivity.this).inflate(R.layout.photo_ablum_item, null);
                viewHolder.mImageView = (PhotoAlbumImageView) convertView.findViewById(R.id.child_image);
                viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.child_checkbox);

                // 用来监听ImageView的宽和高
                viewHolder.mImageView.setOnMeasureListener(new PhotoAlbumImageView.OnMeasureListener() {

                    @Override
                    public void onMeasureSize(int width, int height) {
                        mPoint.set(width, height);
                    }
                });
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Glide.with(ShowPhotoAlbumActivity.this)
                    .load(ImageLoadUrlFitter.fitterUrl(pathList.get(position).getPath()))
                    .into(viewHolder.mImageView);
            viewHolder.mCheckBox.setChecked(mSelectMap
                    .containsKey(position) ? mSelectMap
                    .get(position) : false);

            convertView.setOnClickListener(new OnImageClickListener(position,viewHolder.mCheckBox));

            return convertView;
        }

        private class ViewHolder {
            public PhotoAlbumImageView mImageView;
            public CheckBox mCheckBox;
        }
    }

    /**
     * 点击事件
     */
    private class OnImageClickListener implements OnClickListener {
        private int position;
        private CheckBox checkBox;

        public OnImageClickListener(int position, CheckBox checkBox) {
            this.position = position;
            this.checkBox = checkBox;
        }

        @Override
        public void onClick(View v) {
            boolean isChecked = pathList.get(position).isCheck();
            pathList.get(position).setCheck(!isChecked);
            if (isChecked) {
                mSelectMap.remove(position);
            } else {
                if (mSelectMap.size() >= seleteCount) {
                    showToast("最多只能选择" + seleteCount + "张");
                    return;
                }
                mSelectMap.put(position, !isChecked);
            }
            checkBox.setChecked(mSelectMap
                    .containsKey(position) ? mSelectMap
                    .get(position) : false);
            mHandler.sendEmptyMessage(Constants.REFRESH_SUCCESS2);
        }
    }
}
