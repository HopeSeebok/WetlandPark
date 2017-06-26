package com.yunwei.wetlandpark.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/11/4 11:46
 * @description:
 */

public class FormSpinnerSelectorView extends FormClickableView {

    private PopupWindow mPopupWindow;
    private List<String> mStringList;
    private ListView mListView;
//    private View mPopContentView;

    public FormSpinnerSelectorView(Context context) {
        super(context, null);
    }

    public FormSpinnerSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDataSource(final Context context,List<String> stringList) {
        this.mStringList = stringList;
        if (mStringList != null) {
            mListView = new ListView(context);
            formSelectorContentTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.item_form_spinner_selector, mStringList);
                    mListView.setAdapter(arrayAdapter);
                    mPopupWindow = new PopupWindow(mListView, v.getWidth(), LayoutParams.WRAP_CONTENT, true);
                    mPopupWindow.setTouchable(true);
                    mPopupWindow.setOutsideTouchable(true);
                    mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xEE000000));
                    mPopupWindow.showAsDropDown(v);

                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mPopupWindow.dismiss();
                            setContentText(mStringList.get(position));
                        }
                    });
                }
            });
        } else {
            formSelectorContentTv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(context, "数据为空");
                }
            });
        }
    }
}
