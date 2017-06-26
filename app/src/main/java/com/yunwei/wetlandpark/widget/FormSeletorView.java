package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;

/**
 * @Package: com.yunwei.zaina.widget
 * @Description:
 * @author: Aaron
 * @date: 2016-06-16
 * @Time: 14:03
 * @version: V1.0
 */
public class FormSeletorView extends LinearLayout implements View.OnClickListener {

    private TextView nameText;
    private TextView contentText;
    private ImageView seletorBtn;

    private String name;
    private String hint;

    private OnFormSeletorClickListener listener;

    public FormSeletorView(Context context) {
        super(context);
        initAttributeset(context, null, 0);
        initView();
    }

    public FormSeletorView(Context context, AttributeSet attri) {
        super(context, attri);
        initAttributeset(context, attri, 0);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.form_seletors_layout, null);

        nameText = (TextView) view.findViewById(R.id.form_seletor_title);
        contentText = (TextView) view.findViewById(R.id.form_seletor_content);
        seletorBtn = (ImageView) view.findViewById(R.id.form_seletor_btn);

        seletorBtn.setOnClickListener(this);

        setPropertyNameText(name);

        if (!TextUtils.isEmpty(hint)) {
            contentText.setHint(hint);
        }

        addView(view);
    }

    /**
     * CBOK +
     * 设置不可编辑方法
     */
    public void setNonEditable(){
        seletorBtn.setVisibility(GONE);
    }
    /**
     * 初始化自定义属性值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    private void initAttributeset(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSeletorView, defStyle, 0);
        name = typedArray.getString(R.styleable.FormSeletorView_name_seletor);
        hint = typedArray.getString(R.styleable.FormSeletorView_hint_seletor);

        typedArray.recycle();
    }


    public void setPropertyNameText(String name) {
        if (!TextUtils.isEmpty(name)){
            nameText.setText(name+":");
        }else {
            nameText.setText("Name:");
        }
    }

    public void setPropertyContentText(String content) {
        contentText.setText(content);
    }

    public String getContentTextView() {
        return contentText.getText().toString();
    }

    public void setListener(OnFormSeletorClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onFormSeletorClick();
        }
    }

    /**
     * 监听接口
     */
    public interface OnFormSeletorClickListener {
        void onFormSeletorClick();
    }
}
