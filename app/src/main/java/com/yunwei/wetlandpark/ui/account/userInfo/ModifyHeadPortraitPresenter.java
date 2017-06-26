package com.yunwei.wetlandpark.ui.account.userInfo;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yunwei.library.data.Image;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.ui.account.AccountContract;
import com.yunwei.wetlandpark.ui.account.AccountContract.ModifyHeadPortraitView;
import com.yunwei.wetlandpark.ui.account.login.data.UserInfoEntity;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.ModifyUserInfoRepo;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.ModifyUserInfoSource;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.QiNiuDataSource;
import com.yunwei.wetlandpark.ui.account.userInfo.data.source.QiNiuImageUploadRepo;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.utils.IFileUtils;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.qiniu.UploadCallBackListener;
import com.yunwei.library.utils.BitmapUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account
 * @Description: 修改个人信息
 * @date 2016/11/15 16:10
 */

public class ModifyHeadPortraitPresenter implements AccountContract.ModifyHeadPortraitPresenter, ModifyUserInfoSource.ModifyUserInfoCallBack, UploadCallBackListener {

    private ModifyHeadPortraitView modifyHeadPortraitView;
    private ModifyUserInfoSource modifyHeadPortraitSource;
    private QiNiuDataSource qiNiuDataSource;
    private boolean isUpload = false;
    private String photoUrl;

    public ModifyHeadPortraitPresenter(ModifyHeadPortraitView modifyUserHeadPortraitView){
        this.modifyHeadPortraitView = modifyUserHeadPortraitView;
        modifyHeadPortraitSource = new ModifyUserInfoRepo();
        qiNiuDataSource = new QiNiuImageUploadRepo();
    }

    @Override
    public void modifyHeadPortrait() {
        String photoPath = modifyHeadPortraitView.getNewHeadPortraitImagePath();
        photoCut(photoPath);
    }

    /**
     * 图片处理
     *
     * @param path
     */
    private void photoCut(String path) {
        List<String> lists = new ArrayList<>();
        lists.add(path);
        photoCut(lists);
    }

    /**
     * 图片处理
     *
     * @param list
     */
    private void photoCut(final List<String> list) {
        if (list == null && list.size() == 0) {
            return;
        }
        modifyHeadPortraitView.showUploadDialog();
        new Thread() {
            @Override
            public void run() {
                //标记是否异常
                boolean flag = false;
                try {
                    for (int i = 0; i < list.size(); i++) {
                        final String path = list.get(i);
                        String savePath = IFileUtils.getImageCatchDir();
                        String photoPath= BitmapUtils.compressBitmap(path, savePath);
                        if (!TextUtils.isEmpty(photoPath)) {
                            List<String> uploadList = new ArrayList<>();
                            uploadList.add(savePath);
                            //上传图片到公网
                            qiNiuDataSource.uploadImage(
                                    uploadList,
                                    ModifyHeadPortraitPresenter.this);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    flag = true;
                }finally {
                    if (flag) {
                        modifyHeadPortraitView.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                modifyHeadPortraitView.dismissUploadDialog();
                            }
                        });
                    }
                }

            }
        }.start();
    }

    ////////////////////////////////////////////
    @Override
    public void onUploadStart() {
        isUpload = true;
    }

    @Override
    public void onUploadEnd() {

    }

    @Override
    public void onProgess(double percent) {

    }

    @Override
    public void onUploadComplete(List<Image> path) {
        photoUrl = path.get(0).getUrl();
        UserInfoEntity entity = new UserInfoEntity();
        entity.setId(ZNAPPlication.getUserInfoEntity(modifyHeadPortraitView.getActivity()).getId());
        entity.setIcon(photoUrl);
        entity.setName(ZNAPPlication.getUserInfoEntity(modifyHeadPortraitView.getActivity()).getName());
        //TODO: 16/11/2  一个默认的单位ID
        entity.setUnitID(1);
        //更新到服务器
        modifyHeadPortraitSource.uploadUserInfo(entity, this);
    }

    @Override
    public void onUploadFailure() {

    }

    /////////////////////////////////////////////
    @Override
    public void onModifyStart() {
        if (!isUpload){
            modifyHeadPortraitView.showUploadDialog();
        }
    }

    @Override
    public void onModifySuccess() {
        modifyHeadPortraitView.setImageView(photoUrl);
        String json = ISpfUtil.getValue(modifyHeadPortraitView.getActivity(), Constants.LOGIN_INFO_KEY,"").toString();
        if (!TextUtils.isEmpty(json)){
            Gson gson = new Gson();
            UserInfoEntity entity = gson.fromJson(json,UserInfoEntity.class);
            entity.setIcon(photoUrl);
            String new_json = gson.toJson(entity);
            ISpfUtil.setValue(modifyHeadPortraitView.getActivity(), Constants.LOGIN_INFO_KEY, new_json);
            ZNAPPlication.getInstance().clearUserInfo();
        }
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE6);
        EventBus.getDefault().post(event);
        modifyHeadPortraitView.showUploadSuccess();
    }

    @Override
    public void onModifyFailed(String msg) {
        modifyHeadPortraitView.showUploadFailedMsg(msg);
    }

    @Override
    public void onModifyEnd() {
        modifyHeadPortraitView.dismissUploadDialog();
    }
}
