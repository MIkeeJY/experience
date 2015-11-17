package cn.org.cfpamf.data.sql;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.org.cfpamf.data.database.DatabaseManager;
import cn.org.cfpamf.data.sql.db.Baidu;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by zzy on 15/11/17.
 */
public class BaiduDbManager extends DatabaseManager<Baidu, String> {

    public BaiduDbManager(@NonNull Context context) {
        super(context);
    }

    @Override
    public AbstractDao<Baidu, String> getAbstractDao() {
        return getDaoSession().getBaiduDao();
    }
}
