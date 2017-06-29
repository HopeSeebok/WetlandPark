package com.yunwei.wetlandpark.greedao.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yunwei.wetlandpark.greedao.CheckingFormDao;
import com.yunwei.wetlandpark.greedao.DaoMaster;
import com.yunwei.wetlandpark.greedao.SignInTableDao;

/**
 * Created by hopeseebok on 28/06/2017.
 */

public class DaoMasterOpenHelper extends DaoMaster.OpenHelper {


    public DaoMasterOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        MigrationHelper.getInstance().migrate(sqLiteDatabase, SignInTableDao.class);
    }
}
