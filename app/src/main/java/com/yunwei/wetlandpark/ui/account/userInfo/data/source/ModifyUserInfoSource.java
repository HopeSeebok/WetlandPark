package com.yunwei.wetlandpark.ui.account.userInfo.data.source;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.data.source
 * @Description:修改用户信息
 * @date 2016/11/15 16:32
 */

public interface ModifyUserInfoSource {

    interface ModifyUserInfoCallBack {
        void onModifyStart();
        void onModifySuccess();
        void onModifyFailed(String msg);
        void onModifyEnd();
    }

    void uploadUserInfo(Object o, ModifyUserInfoCallBack modifyUserInfoCallBack);
}
