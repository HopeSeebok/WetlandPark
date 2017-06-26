package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.presenter;

import android.content.Context;
import android.os.Message;

import com.ogaclejapan.rx.binding.RxProperty;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntity;
import com.yunwei.wetlandpark.greedao.TaskListAndDetailEntityDao;
import com.yunwei.wetlandpark.ui.adapter.task.TaskListAdapter;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.view.interfaceView.TaskListView;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import java.util.List;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.presenter.task
 * @Description:处理任务列表逻辑
 * @date 2016/9/26
 * @changeby:
 */

public class TaskListPresenter {
    private  Context mContext;
    private final TaskListView mTaskListView;
    private List<TaskListAndDetailEntity> mList;
    private RxProperty<List<TaskListAndDetailEntity>> mListRxProperty;
    private TaskListAdapter mAdapter;

    public TaskListPresenter(Context context,final TaskListView taskListView) {
        mContext=context;
        mTaskListView = taskListView;
        //初始化adapter
        mAdapter = new TaskListAdapter(mContext);
    }

    public void initData() {
//        //查询数据库
//        getTaskListData();
//        //初始化界面
//        setAdapterLists();
//        mTaskListView.setTaskListAdapter(mAdapter);
//        //绑定recycleview
//        mListRxProperty = RxProperty.create();
//        mTaskListView.bindingView(mListRxProperty, mAdapter);
    }

    public void getTaskListData(int PAGE_COUNT) {
        ZNAPPlication.getDaoSession().clear();
        if (mList!=null) {
            mList.clear();
        }
        String mUserId = (String) ISpfUtil.getValue(mTaskListView.getActivity(), Constants.ACCOUNT_ID_KEY, "");
        mList = ZNAPPlication.getDaoSession().getTaskListAndDetailEntityDao().queryBuilder().where(
                TaskListAndDetailEntityDao.Properties.UserId.eq(mUserId),
                TaskListAndDetailEntityDao.Properties.IsHistory.eq(false)
        ).orderDesc(TaskListAndDetailEntityDao.Properties.ProcessingTime).limit(PAGE_COUNT).list();
    }


    public void refreshList(Message msg) {
        List<TaskListAndDetailEntity> list = (List<TaskListAndDetailEntity>)msg.obj;
        mAdapter.clearList();
        if (list == null || list.size() == 0){
            mTaskListView.setNodataViewVisiable(true);
            mAdapter.setShowFootFlag(false);
        }else {
            mAdapter.setLists(list);
            mTaskListView.setNodataViewVisiable(false);
        }
    }

    public void uploadList(Message msg) {
        List<TaskListAndDetailEntity> list = (List<TaskListAndDetailEntity>)msg.obj;
        if (list==null||list.size()==0){
            mAdapter.setShowFootFlag(false);
        }else {
            mAdapter.addItems(list);
        }
    }

    public void setAdapterLists() {
        mAdapter.setLists(mList);
    }

    public List<TaskListAndDetailEntity> getLists() {
        return mList;
    }

    public void setShowFootFlag(boolean b) {
        mAdapter.setShowFootFlag(b);
    }

    public void setAdapter() {
        mTaskListView.setTaskListAdapter(mAdapter);
    }

    public void notifyData() {
        mAdapter.notifyDataSetChanged();
    }
}
