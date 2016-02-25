package cn.org.cfpamf.data.sql;

import cn.org.cfpamf.data.database.AbstractDatabaseManager;
import cn.org.cfpamf.data.sql.db.Baidu;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by zzy on 15/11/17.
 */
public class BaiduDbManager extends AbstractDatabaseManager<Baidu, String> {


    @Override
    public AbstractDao<Baidu, String> getAbstractDao() {
        return daoSession.getBaiduDao();
    }
}
