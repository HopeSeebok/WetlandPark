package com.yunwei.wetlandpark.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.search.SearchKey;
import com.yunwei.wetlandpark.ui.biz.interfac.SearchListener;

import java.util.ArrayList;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.interfac.task
 * @Description:自定义搜索框
 * @date 2016/9/27
 * @changeby:
 */
public class SearchDialog extends Dialog {

    private final SearchListener mSearchListener;
    private final Context mContext;

    public SearchDialog(Context context, SearchListener searchListener){
        super(context);
        mContext=context;
        mSearchListener=searchListener;
    }

    public void showDialog(int layoutResID, int x, int y){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutResID);
        windowDeploy(x, y);

        //设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        show();

        Button searchIv=(Button) findViewById(R.id.search_bt);
        final EditText searchEt=(EditText) findViewById(R.id.search_et);
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchListener.searchClick(searchEt.getText().toString());
            }
        });

        LinearLayout keyLl = (LinearLayout) findViewById(R.id.key_ll);
        SearchKey searchKey = new SaveObjectUtils(mContext, Constants.SEARCHKEY).getObject(Constants.KEY, SearchKey.class);
        if (searchKey==null) {
            new SaveObjectUtils(mContext, Constants.SEARCHKEY).setObject(Constants.KEY,new SearchKey().setKey(new ArrayList<String>()));
        }else {
            ArrayList<String> keys = searchKey.getKey();
            for (int i = 0; i < keys.size(); i++) {
                final TextView textView=new TextView(mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
                textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                textView.setText(keys.get(i));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchEt.setText(textView.getText());
                    }
                });
                keyLl.addView(textView);
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager input = (InputMethodManager) mContext.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                input.showSoftInput(searchEt, 0);
            }
        }, 700);
    }

    //设置窗口显示
    private void windowDeploy(int x, int y){
        Window window = getWindow();
        if (window==null) {
            return;
        }
        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.luency); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        wl.x = x; //x小于0左移，大于0右移
        wl.y = y; //y小于0上移，大于0下移
//            wl.alpha = 0.6f; //设置透明度
        wl.gravity = Gravity.TOP; //设置重力
        window.setAttributes(wl);
    }
}