package jiyun.com.openresausechina.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import jiyun.com.openresausechina.model.AutoLogin;
import jiyun.com.openresausechina.model.dao.AutoLoginDao;
import jiyun.com.openresausechina.model.dao.DaoMaster;
import jiyun.com.openresausechina.model.dao.DaoSession;

/**
 * Created by think on 2017/4/10.
 */

public class DbManger {
    private final SQLiteDatabase database;

    public DbManger(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"user.db");
        database = helper.getReadableDatabase();
    }

    public boolean insertData(AutoLogin autoLogin){
        DaoMaster master=new DaoMaster(database);

        DaoSession daoSession = master.newSession();

        AutoLoginDao autoLoginDao = daoSession.getAutoLoginDao();

        long count=autoLoginDao.insert(autoLogin);

        if (count>0)
            return true;
        return false;
    }
}
