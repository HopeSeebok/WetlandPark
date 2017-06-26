package com.yunwei.wetlandpark.ui.presenter;


import com.yunwei.wetlandpark.ui.biz.IToken;
import com.yunwei.wetlandpark.ui.biz.interfac.RequestTokenListener;
import com.yunwei.wetlandpark.ui.biz.impl.TokenBiz;
import com.yunwei.wetlandpark.ui.view.TokenView;

/**
 * 
 * @ClassName: TokenPresenter
 * @Description: Token请求控制层
 * @author wuhezhi
 * @date 2016-7-2 下午4:23:59
 * 
 */
public class TokenPresenter implements RequestTokenListener {

	private IToken tokenBiz;
	private TokenView tokenView;

	public TokenPresenter(TokenView tokenView) {
		this.tokenView = tokenView;
		this.tokenBiz = new TokenBiz();
	}

	public void requestToken() {
		tokenBiz.requestToken(tokenView.getActivity(), this);
	}

	@Override
	public void getTokenStart() {
		if (tokenView != null) {
			tokenView.reqTokenStart();
		}

	}

	@Override
	public void getTokenSuccess(String token) {
		if (tokenView != null) {
			tokenView.reqTokenSuccess(token);
		}
	}

	@Override
	public void getTokenFailure() {

	}
}
