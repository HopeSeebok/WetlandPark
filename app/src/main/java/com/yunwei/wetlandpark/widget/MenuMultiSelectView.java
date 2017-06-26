package com.yunwei.wetlandpark.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.adapter.ArrayListAdapter;
import com.yunwei.library.utils.IDensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.hydrant.widget
 * @Description:多选菜单样式Layout
 * @date 2016/10/18 9:06
 */

public class MenuMultiSelectView extends LinearLayout {
    private final String TAG = getClass().getSimpleName();

    private Context context;

    private ListView menuListListView;
    private MenuSeletorAdapter adapter;

    private List<String> selectContent;

    public MenuMultiSelectView(Context context) {
        super(context);
        init();
    }

    public MenuMultiSelectView(Context context, AttributeSet attri) {
        super(context, attri);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_list_layout, null);
        menuListListView = ButterKnife.findById(view, R.id.menu_list_listView);

        adapter = new MenuSeletorAdapter((Activity) getContext());
        menuListListView.setAdapter(adapter);

        this.context = getContext();
        this.selectContent = new ArrayList<>();

        addView(view);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int measuredHeight = measureHeight(heightMeasureSpec);
//        //设置最大高度
////        int maxHeight = IDensityUtils.getScreenH((Activity) context) / 2;
////        if (heightSize > maxHeight) {
////            heightSize = maxHeight;
////        }
////        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        // Default size if no limits are specified.
        int result = IDensityUtils.getScreenH((Activity) context) / 2;
        if (specMode == MeasureSpec.AT_MOST) {
        // Calculate the ideal size of your
        // control within this maximum size.
        // If your control fills the available
        // space return the outer bound.
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
        // If your control can fit within these bounds return that value.
            result = specSize;
        }
        return result;
    }


    /**
     * 设置资源
     *
     * @param list
     */
    public void setDataSource(List<String> list) {
        adapter.appendToList(list);
    }

    /**
     * 设置资源
     *
     * @param data
     */
    public void setDataSource(String[] data) {
        adapter.appendToList(data);
    }

    /**
     * 返回选择的item Content
     *
     * @return
     */
    public List<String> getSelectContent() {
        return selectContent;
    }

    /**
     * 设置已选择的item
     *
     * @param list
     */
    public void setSelectContent(List<String> list) {
        for (String str : list) {
            selectContent.add(str);
        }
    }


    private class MenuSeletorAdapter extends ArrayListAdapter<String> {

        public MenuSeletorAdapter(Activity context) {
            super(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_list_item, null);
            TextView nameTv = ButterKnife.findById(convertView, R.id.menu_item_name_tv);
            CheckBox checkIv = ButterKnife.findById(convertView, R.id.menu_item_checkBox);

            nameTv.setText(mList.get(position));

            if (selectContent.contains(mList.get(position))) {
                checkIv.setChecked(true);
            }

            checkIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectContent.add(mList.get(position));
                    } else {
                        selectContent.remove(mList.get(position));
                    }
                }
            });
            return convertView;
        }
    }
}
