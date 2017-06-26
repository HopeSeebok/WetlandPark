package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.widget
 * @Description:标记巡查打卡记录的圆球,不可在xml文件中添加
 * @date 2016/12/02 14:02
 */

public class BallTextView extends LinearLayout{

    private int negativeImageId = R.mipmap.uncheck,
                positiveImageId = R.mipmap.checked;

    private boolean mState = false;//默认false negative
    private float textSize = 12;
    private int color = Color.WHITE;

    private TextView textView;
    private ImageView imageView;

    public BallTextView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        setmState(mState);
        textView = new TextView(context);
        imageView = new ImageView(context);
        //设置imageview
        imageView.setImageResource(R.mipmap.check_pencil);
        LayoutParams params_imageView = new LayoutParams(40, 40);
        imageView.setLayoutParams(params_imageView);
        addView(imageView);
        //设置textview
        LayoutParams params_textView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params_textView);
        textView.setTextColor(color);
        textView.setTextSize(textSize);
        addView(textView);
    }

    public void setNegativeImageId(int negativeImageId) {
        this.negativeImageId = negativeImageId;
        setmState(mState);
    }

    public void setPositiveImageId(int positiveImageId) {
        this.positiveImageId = positiveImageId;
        setmState(mState);
    }

    public String getContentText() {
        return textView.getText().toString();
    }

    public void setContentText(String mText) {
        textView.setText(mText);
    }

    public void setmState(boolean mState) {
        this.mState = mState;
        if (mState){
            setBackgroundResource(positiveImageId);
        }else{
            setBackgroundResource(negativeImageId);
        }
    }
}
