package com.yunwei.wetlandpark.ui.presenter;

import com.yunwei.wetlandpark.entity.RegeoEntity;
import com.yunwei.wetlandpark.ui.biz.IRegeo;
import com.yunwei.wetlandpark.ui.biz.impl.RegeoBiz;
import com.yunwei.wetlandpark.ui.biz.interfac.RegeoLisenter;
import com.yunwei.wetlandpark.ui.view.RegeoView;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter
 * @Description:逆地理编码控制层
 * @date 2016/9/19 11:30
 */
public class RegeoPresenter implements RegeoLisenter {

    private IRegeo regeo;
    private RegeoView regeoView;

    public RegeoPresenter(RegeoView regeoView){
        this.regeoView=regeoView;
        this.regeo=new RegeoBiz();
    }

    public void regeo(double lng,double lat){
        regeo.regeo(regeoView.getActivity(),lng,lat,this);
    }
    @Override
    public void onRegeoStart() {
        this.regeoView.onRegeoStart();
    }

    @Override
    public void onRegeoEnd() {
        regeoView.onRegeoEnd();
    }

    @Override
    public void onRegeoFailure(int code, String error) {
        regeoView.onRegeoFailure(code,error);
    }

    @Override
    public void onRegeoSuccess(RegeoEntity entity) {
        regeoView.onRegeoSuccess(entity);
    }
}
