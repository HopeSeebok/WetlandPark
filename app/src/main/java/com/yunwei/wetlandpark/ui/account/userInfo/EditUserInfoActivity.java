package com.yunwei.wetlandpark.ui.account.userInfo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.AccessoryPopupUtil;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.account.password.PasswordEditActivity;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.common.ShowPhotoAlbumActivity;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.view.RoundedBitmapImageViewTarget;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.IStringUtils;

import java.io.File;
import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.water.ui.activity.mine
 * @Description:个人信息修改界面
 * @date 2016/9/7 16:00
 */
public class EditUserInfoActivity extends BaseActivity implements AccessoryPopupUtil.AccessSeletorListener, AccountContract.ModifyHeadPortraitView {

    private final String TAG = "EditUserInfoActivity";

    private ImageView headIV;

    private String photoPath;
    private String savePath;
    private String updateHeadUrl;//上传到网络的图片url
    public final static int PHOTO = 421;
    private boolean isUpload = false;//标记是否上传了图片

    private AccountContract.ModifyHeadPortraitPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_user_info);
        setToolbarTitle("个人信息修改");
        presenter = new ModifyHeadPortraitPresenter(this);
    }

    @Override
    public void findViewById() {
        super.findViewById();
        headIV = (ImageView) findViewById(R.id.edit_user_head_iv);

        findViewById(R.id.user_info_pwd_edit).setOnClickListener(this);
        findViewById(R.id.user_info_photo_edit).setOnClickListener(this);

        setImageView(ZNAPPlication.getUserInfoEntity(getActivity()).getIcon());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.user_info_photo_edit:
                AccessoryPopupUtil.showAccessorySeletorDialog(this, v, this);
                break;
            case R.id.user_info_pwd_edit:
                ISkipActivityUtil.startIntent(this, PasswordEditActivity.class);
                break;
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));
            startActivityForResult(intent, PHOTO);
        } else if (AccessoryPopupUtil.ALBUM.equals(msg)) {//相册
            Bundle bundle = new Bundle();
            bundle.putInt(ShowPhotoAlbumActivity.SELETE_COUNT, 1);
            ISkipActivityUtil.startIntentForResult(this, ShowPhotoAlbumActivity.class, bundle, ShowPhotoAlbumActivity.ALBUM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ShowPhotoAlbumActivity.ALBUM) {//相册
            List<String> list = data.getStringArrayListExtra("list");
            photoPath = list.get(0);
        }
        if (requestCode == PHOTO && resultCode == Activity.RESULT_OK) {//拍照

        }
        presenter.modifyHeadPortrait();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public String getNewHeadPortraitImagePath() {
        return photoPath;
    }

    @Override
    public void showUploadSuccess() {
        showToast("头像修改成功");
    }

    @Override
    public void showUploadDialog() {
        dialog = DialogFactory.createLoadingDialog(this, "修改...");
    }

    @Override
    public void dismissUploadDialog() {
        DialogFactory.dimissDialog(dialog);
    }

    @Override
    public void showUploadFailedMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setImageView(String path) {
        Glide.with(getActivity()).load(path).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(headIV));
    }
}
