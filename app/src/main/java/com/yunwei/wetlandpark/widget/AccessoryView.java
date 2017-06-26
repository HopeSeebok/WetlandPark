package com.yunwei.wetlandpark.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.yunwei.wetlandpark.utils.IDateTimeUtils;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.AccessoryPopupUtil;
import com.yunwei.wetlandpark.ui.common.ShowImageActivity;
import com.yunwei.wetlandpark.ui.common.ShowPhotoAlbumActivity;
import com.yunwei.wetlandpark.ui.adapter.AccessoryImgAdapter;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.view.AccessoryImageGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.yunwei.zaina.widget
 * @Description:附件Layout
 * @author: Aaron
 * @date: 2016-06-14
 * @Time: 18:50
 * @version: V1.0
 */
public class AccessoryView extends LinearLayout implements AdapterView.OnItemClickListener, AccessoryPopupUtil.AccessSeletorListener {

    private AccessoryImageGridView gridView;
    private AccessoryImgAdapter adapter;

    private Activity activity;

    public final static int PHOTO = 421;

    private String photoPath;

    public AccessoryView(Activity context) {
        super(context);
        this.activity = context;
        initView(context);
    }

    public AccessoryView(Activity context, AttributeSet attri) {
        super(context, attri);
        this.activity = context;
        initView(context);
    }

    private void initView(Activity context) {
        View view = LayoutInflater.from(context).inflate(R.layout.accessory_img_layout, null);
        gridView = (AccessoryImageGridView) view.findViewById(R.id.accessory_gridview);

        adapter = new AccessoryImgAdapter(context);
        gridView.setAdapter(adapter);

        initGridView();
        addView(view);
    }

    private void initGridView() {
        adapter.appendToList(AccessoryImgAdapter.ADD_IMG_FLAG);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String entity = (String) adapter.getItem(position);
        if (IStringUtils.isEmpty(entity)) {
            return;
        }
        //添加图片
        if (AccessoryImgAdapter.ADD_IMG_FLAG.equals(entity)) {
            AccessoryPopupUtil.showAccessorySeletorDialog(activity, view, this);
        } else {//查看大图
            ArrayList<String> list = new ArrayList<>();
            for (String string : adapter.getList()) {
                if (!AccessoryImgAdapter.ADD_IMG_FLAG.equals(string))
                    list.add(string);
            }
            Bundle bundle = new Bundle();
            bundle.putInt(ShowImageActivity.SHOW_IMG_INDEX, position);
            bundle.putStringArrayList(ShowImageActivity.SHOW_IMG_LIST, list);
            ISkipActivityUtil.startIntent(getContext(), ShowImageActivity.class, bundle);
        }
    }

    @Override
    public void onAccessItemClick(String msg) {
        if (IStringUtils.isEmpty(msg))
            return;
        if (AccessoryPopupUtil.PHOTOGRAPH.equals(msg)) {//拍照
            photoPath = IFileUtils.getImgPath();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);

//            File mMediaDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
//            File mFile = new File(mMediaDir.getPath() + File.separator + "IMG_" + IDateTimeUtils.formatDate(System.currentTimeMillis()) + ".jpg");
//            photoPath = mFile.getAbsolutePath();
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));

            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));
            activity.startActivityForResult(intent, PHOTO);
        } else if (AccessoryPopupUtil.ALBUM.equals(msg)) {//相册
            Bundle bundle = new Bundle();
            bundle.putInt(ShowPhotoAlbumActivity.SELETE_COUNT, 10);
            ISkipActivityUtil.startIntentForResult(activity, ShowPhotoAlbumActivity.class, bundle, ShowPhotoAlbumActivity.ALBUM);
        }
    }

    /**
     * 添加相册图片
     *
     * @param list
     */
    public void setImgList(List<String> list) {
        if(list==null&&list.size()==0){
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (adapter.getList().size() > 10) {
                adapter.removePos(adapter.getList().size() - 1);
                break;
            } else {
                setImgList(list.get(i));
            }
        }
    }

    /**
     * 添加拍照图片
     *
     * @param list
     */
    public void setImgList(String list) {
        if ("photo".equals(list) && !IStringUtils.isEmpty(photoPath)) {
            adapter.appendPositionToList(0, photoPath);
        } else {
            adapter.appendPositionToList(0, list);
        }
        if (adapter.getList().size() > 10) {
            adapter.removePos(adapter.getList().size() - 1);
            return;
        }
    }

    /**
     * 设置显示图片
     *
     * @param strs
     */
    public void setShowImageList(String[] strs) {
        adapter.setShowCloseIcon(false);
        if (adapter.getList().get(adapter.getCount() - 1).equals(AccessoryImgAdapter.ADD_IMG_FLAG)) {
            adapter.removePos(adapter.getCount() - 1);
            adapter.appendToList(strs);
        }
    }

    /**
     *设置显示图片方法2，保留+号按钮
     */
    @Deprecated
    public void setShowImageListWithAddButton(String[] strs) {
        adapter.setShowCloseIcon(false);
        if (adapter.getList().get(adapter.getCount() - 1).equals(AccessoryImgAdapter.ADD_IMG_FLAG)) {
            for (int i=0;i<strs.length;i++){
                adapter.appendPositionToList(0,strs[i]);
            }
        }
    }
    /**
     * 设置显示图片
     *
     * @param strs
     */
    public void setShowImageList(ArrayList<String> strs) {
        adapter.setShowCloseIcon(false);
        if (adapter.getList().get(adapter.getCount() - 1).equals(AccessoryImgAdapter.ADD_IMG_FLAG)) {
            adapter.removePos(adapter.getCount() - 1);
            adapter.appendToList(strs);
        }
    }

    /**
     * 设置显示图片
     *
     * @param url
     */
    public void setShowImage(String url) {
        adapter.appendToList(url);
    }

    public void initShowImage() {
        adapter.setShowCloseIcon(false);
        if (adapter.getList().get(adapter.getCount() - 1).equals(AccessoryImgAdapter.ADD_IMG_FLAG)) {
            adapter.removePos(adapter.getCount() - 1);
        }
    }

    /**
     * 获取图片资源
     *
     * @return
     *
     */
    public List<String> getAccContent() {
        List<String> list = new ArrayList<>();
        if (adapter.getCount()>0 &&  adapter.getList().get(adapter.getCount() - 1).equals(AccessoryImgAdapter.ADD_IMG_FLAG)) {
            list.addAll(adapter.getList());
            list.remove(list.size()-1);
        } else {
            list.addAll(adapter.getList());
        }
        return list;
    }

    /**
     * 清空列表图片
     */
    public void clearImages() {
        while ((adapter.getCount()) > 1) {
            adapter.removePos(0);
        }
    }

    /**
     * 返回拍照图片
     *
     * @return
     */
    public String getPhotoPath() {
        return photoPath;
    }
}
