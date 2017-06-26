package com.yunwei.wetlandpark.ui.account.password.data.source;

import com.yunwei.wetlandpark.ui.account.password.data.ChangePwdEntity;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.account.password.data.source
 * @Description:修改密码
 * @date 2016/11/16 09:22
 */

public interface ModifyPasswordDataSource {

    interface ModifyPasswordCallBack{
        void onModifyStart();
        void onModifySuccess();
        void onModifyFailed(String msg);
        void onModifyEnd();
    }

    void modifyPassword(ChangePwdEntity entity, ModifyPasswordCallBack callBack);

}
