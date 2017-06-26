package com.yunwei.wetlandpark.entity;

import java.io.Serializable;

/**
 * GridView��ÿ��item����ݶ���
 * 
 * @author len
 * 
 */
public class ImageBean implements Serializable {

	private String path;
	private boolean isCheck;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

}
