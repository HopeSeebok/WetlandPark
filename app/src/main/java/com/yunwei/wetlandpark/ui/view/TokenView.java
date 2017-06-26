package com.yunwei.wetlandpark.ui.view;

public interface TokenView extends BaseView {

	public void reqTokenStart();

	public void reqTokenSuccess(String token);
	
	public void reqTokenFailure();
}
