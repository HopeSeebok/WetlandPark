package com.yunwei.wetlandpark.ui.biz.interfac;

public interface RequestTokenListener {

	public void getTokenStart();

	public void getTokenSuccess(String token);

	public void getTokenFailure();

}
