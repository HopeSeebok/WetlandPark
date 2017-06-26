package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;

/**
 * @Package: com.yunwei.zaina.ui.activity.set.fragment
 * @Description:意见反馈
 * @author: Aaron
 * @date: 2016-06-26
 * @Time: 15:01
 * @version: V1.0
 */
public class SuggestionFragment extends BaseFragment {

    public static final String SUGGESTION_INFO_FLAG="suggestion_flag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.suggestion_fragment,null);
        return view;
    }
}
