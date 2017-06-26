package com.yunwei.library.data;

import java.io.Serializable;

/**
 * @author CBOK
 * @date 2016/11/9 9:58
 * @description:
 */

public class Image implements Serializable{
    private String Url;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUrl(String Url){
        this.Url = Url;
    }
    public String getUrl(){
        return this.Url;
    }
}
