package com.yunwei.wetlandpark.ui.view;

/**
 * @author WuQianRui
 * @version V1.0
 * @Package com.yunwei.camera.ui.view
 * @Description 用于协定有关提交数据的操作步骤
 * @Date 2016/10/9 .
 */
public interface BaseSubmitView<T> extends BaseView{

    //设置控件的状态 （是编辑还是填报，是可点击还是吧不可点击，都在这里进行设置）
    public void settingView();

    /**
     * 实体类传递给界面的各个控件
     */
    public void entity2View(T t);


    /**
     * 界面控件的值赋值给实体类数据
     */
    public T view2Entity();

}
