package com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch;

import com.esri.core.tasks.identify.IdentifyParameters;
import com.yunwei.wetlandpark.ui.deviceFunctions.searchFunction.tapMapSearch.data.MapEntity;
import com.yunwei.wetlandpark.ui.view.BaseView;

import java.util.List;

/**
 * @author CBOK
 * @date 2016/12/5 22:40
 * @description:
 */

public interface TapMapSearchContract {

    interface View extends BaseView {
        void showTapSearchResult(List<MapEntity> data);

        void showTapSearchError();

        IdentifyParameters getTapSearchParameters();
    }

    interface Presenter {
        void tapSearch();
    }
}
