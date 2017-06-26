package com.yunwei.wetlandpark.common.dialog;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yunwei.library.utils.IDensityUtils;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.TaskPopupAdapter;

/**
 * @Package: com.yunwei.zaina.common.dialog
 * @Description:
 * @author: Aaron
 * @date: 2016-06-13
 * @Time: 15:01
 * @version: V1.0
 */
public class TaskPopupUtil {

    private static PopupWindow popupWindow;

    private static View initView(Activity context,ItemOnClickCallBack callBack) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_pop_layout, null);
        ListView listView = (ListView) view.findViewById(R.id.task_pop_listview);
        listView.setOnItemClickListener(new ItemOnClick(callBack));
        TaskPopupAdapter adapter=new TaskPopupAdapter(context);
        adapter.appendToList("新增设施");
        adapter.appendToList("管线巡查");
        listView.setAdapter(adapter);
        return view;
    }

    private static PopupWindow initPopupWindow(Activity context,ItemOnClickCallBack callBack) {
        PopupWindow popupWindow = new PopupWindow(initView(context,callBack), IDensityUtils.getScreenW(context) / 3, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        return popupWindow;
    }

    public static void showTaskPopupWindow(Activity context, View view,ItemOnClickCallBack callBack) {
        if (popupWindow!=null&&popupWindow.isShowing()) {
            popupWindow.dismiss();
            return;
        }
        popupWindow = initPopupWindow(context,callBack);
        popupWindow.showAsDropDown(view, -5, -5);
    }

    static class ItemOnClick implements AdapterView.OnItemClickListener {
        private ItemOnClickCallBack callBack;
        public ItemOnClick(ItemOnClickCallBack callBack){
            this.callBack=callBack;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
            if (callBack!=null){
                callBack.onPopItemClick(parent.getItemAtPosition(position).toString());
            }
        }
    }

    public interface ItemOnClickCallBack{
        public void onPopItemClick(String str);
    }
}
