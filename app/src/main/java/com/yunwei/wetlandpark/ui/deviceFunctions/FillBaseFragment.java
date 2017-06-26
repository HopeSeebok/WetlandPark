package com.yunwei.wetlandpark.ui.deviceFunctions;

import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.common.ShowPhotoAlbumActivity;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.widget.AccessoryView;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.BitmapUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.activity.fill
 * @Description:填报的BaseFragment类
 * @date 2016/10/9 11:57
 */

public abstract class FillBaseFragment extends BaseFragment {

    protected FrameLayout mContentLayout;
    Button fillBaseFragmentSaveBtn;
    Button fillBaseFragmentSubmitBtn;
    public LinearLayout mBottomLayout;

    private LayoutInflater mInflater;
    /**
     * 附件控件[图片]
     */
    protected AccessoryView mAccessoryView;
    private String savePath;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
        mAccessoryView = new AccessoryView(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill, null);
//        ButterKnife.bind(this, view);
        mContentLayout = (FrameLayout) view.findViewById(R.id.fillBaseFragment_content_fl);
        fillBaseFragmentSaveBtn = (Button) view.findViewById(R.id.fillBaseFragment_save_btn);
        fillBaseFragmentSubmitBtn = (Button) view.findViewById(R.id.fillBaseFragment_submit_btn);
        mBottomLayout = (LinearLayout) view.findViewById(R.id.fillBaseFragment_bottom_layout);
        fillBaseFragmentSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAction();
            }
        });
        fillBaseFragmentSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAction();
            }
        });
        addContentView(inflater);
        return view;
    }

    /**
     * 添加布局文件
     */
    private void addContentView(LayoutInflater inflater) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(getContentView(inflater));
    }


    /**
     * 获取表单布局view
     *
     * @return
     */
    protected abstract View getContentView(LayoutInflater inflater);

    /**
     * 保存
     */
    protected abstract void saveAction();

    /**
     * 提交
     */
    protected abstract void submitAction();

    /**
     * 选择、照片回调
     */
    public void photoResult(int requestCode, int resultCode, Intent data){
            if (resultCode == ShowPhotoAlbumActivity.ALBUM ) {//相册
                List<String> list = data.getStringArrayListExtra("list");
                List<String> timeAdds = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        ExifInterface exifInterface = new ExifInterface(list.get(i));
                        String date = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String dateString = formatter.format(date);
                        timeAdds.add(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                photoCut(list, timeAdds);
            }
            if (requestCode == AccessoryView.PHOTO && resultCode == Activity.RESULT_OK) {//拍照
                photoCut(mAccessoryView.getPhotoPath());
//                getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(mAccessoryView.getPhotoPath()))));
            }
    }

    /**
     * 图片处理
     */
    private void photoCut(String path) {
        List<String> lists = new ArrayList<>();
        lists.add(path);
        photoCut(lists,new ArrayList<>());
    }

    private void photoCut(final List<String> list, final List<String> timeAdds) {
        if (list == null && list.size() == 0) {
            return;
        }
        dialog = DialogFactory.createLoadingDialog(getActivity(), R.string.img_cro);
        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        final String path = list.get(i);
                        savePath = IFileUtils.getImageCatchDir();
                        String date;
                        if (timeAdds.size() == 0) {
                            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            date = sDateFormat.format(new java.util.Date());
                        } else {
                            date = timeAdds.get(i);
                        }
                        final String photoPath = BitmapUtils.compressBitmap(path, savePath, getActivity(),
                                date);
                        if (!TextUtils.isEmpty(photoPath)) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mAccessoryView.setImgList(photoPath);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            DialogFactory.dimissDialog(dialog);
                        }
                    });
                }
            }
        }.start();
    }

    /**
     * 保存按钮设置为不可见
     * by:huangyue
     */
    public void setSaveButtonInvisible(){
        fillBaseFragmentSaveBtn.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)fillBaseFragmentSubmitBtn.getLayoutParams();
        params.leftMargin = 0;
        fillBaseFragmentSubmitBtn.setLayoutParams(params);
    }
}
