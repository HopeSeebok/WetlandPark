package com.yunwei.wetlandpark.entity.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.entity.search
 * @Description:
 * @date 2016/9/27
 * @changeby:
 */

public class SearchKey implements Serializable {
    ArrayList<String> key;

    public ArrayList<String> getKey() {
        return key;
    }

    public SearchKey setKey(ArrayList<String> key) {
        this.key = key;
        return this;
    }

}
