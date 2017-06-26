package com.yunwei.wetlandpark.common.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.TaskPopupAdapter;

import java.util.List;

/**
 * @Package: com.yunwei.zaina.common.dialog
 * @Description:
 * @author: Aaron
 * @date: 2016-06-16
 * @Time: 14:17
 * @version: V1.0
 */
public class FormSeletorPopupUtil {
    private static PopupWindow popupWindow;

    private static View initView(Activity context,List<String> list) {
        View view = LayoutInflater.from(context).inflate(R.layout.form_pop_layout, null);
        ListView listView = (ListView) view.findViewById(R.id.form_pop_listview);
        view.findViewById(R.id.form_pop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        listView.setOnItemClickListener(new ItemOnClick());
        TaskPopupAdapter adapter=new TaskPopupAdapter(context);
        listView.setAdapter(adapter);
        adapter.appendToList(list);
        return view;
    }

    private static PopupWindow initPopupWindow(Activity context,List<String> list) {
        PopupWindow popupWindow = new PopupWindow(initView(context,list), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x98000000));
        return popupWindow;
    }

    public static void showFormPopupWindow(Activity context,List<String> list,View view) {
        if (popupWindow!=null&&popupWindow.isShowing()) {
            popupWindow.dismiss();
            return;
        }
        popupWindow = initPopupWindow(context,list);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    static class ItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
            if (listener!=null){
                listener.onClickResult(position,parent.getItemAtPosition(position).toString());
            }
        }
    }

    private static ForemSeletorListener listener;

    public static void setListener(ForemSeletorListener listener){
        FormSeletorPopupUtil.listener=listener;
    }

    public interface ForemSeletorListener{
        public void onClickResult(int position,String result);
    }

}
