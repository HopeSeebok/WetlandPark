package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.RequestTokenListener;

public interface IToken {
	
	public void requestToken(Activity activity, RequestTokenListener listener);

}
