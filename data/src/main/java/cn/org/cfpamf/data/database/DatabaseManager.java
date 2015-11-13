package cn.org.cfpamf.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.org.cfpamf.data.sql.dao.DaoMaster;
import cn.org.cfpamf.data.sql.dao.DaoSession;
import cn.org.cfpamf.data.util.StringUtils;
import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;


/**
 * @author Octa
 */
public class DatabaseManager<M> implements AsyncOperationListener, IDatabase<M> {

    private static final String DEFAULT_DATABASE_NAME = "name.db";

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    protected static DaoMaster.DevOpenHelper mHelper;
    protected static SQLiteDatabase database;
    protected static DaoMaster daoMaster;
    protected static DaoSession daoSession;
    protected static AsyncSession asyncSession;
    protected static List<AsyncOperation> completedOperations = new CopyOnWriteArrayList<>();
    protected Context context;
    protected String dbName;

    /**
     * create new DataBase
     */
    public DatabaseManager(@NonNull Context context) {
        this.context = context;
        this.dbName = DEFAULT_DATABASE_NAME;
        getOpenHelper(context,dbName);
    }

    /**
     * create new DataBase
     */
    public DatabaseManager(@NonNull Context context, @NonNull String dataBaseName) {
        this.context = context;
        this.dbName = dataBaseName;
        getOpenHelper(context, dataBaseName);
    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);
    }

    /**
     * Query for readable DB
     */
    protected void openReadableDb() throws SQLiteException {
        database = getOpenHelper(context, dbName).getReadableDatabase();
        getDaoMaster();
        getDaoSession();
    }

    /**
     * Query for writable DB
     */
    protected void openWritableDb() throws SQLiteException {
        database = getOpenHelper(context, dbName).getWritableDatabase();
        getDaoMaster();
        getDaoSession();
    }

    /**
     * Query for readable DB
     */
    protected void openReadableDbAsync() throws SQLiteException {
        database = getOpenHelper(context, dbName).getReadableDatabase();
        getDaoMaster();
        getDaoAsyncSession();
    }

    /**
     * Query for writable DB
     */
    protected void openWritableDbAsync() throws SQLiteException {
        database = getOpenHelper(context, dbName).getWritableDatabase();
        getDaoMaster();
        getDaoAsyncSession();
    }

    /**
     * 初始化DatabaseHelper
     */
    protected DaoMaster.DevOpenHelper getOpenHelper(@NonNull Context context, @Nullable String dataBaseName) {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, dataBaseName, null);
        }
        return mHelper;
    }

    /**
     * 初始化DaoMaster
     */
    private void getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(database);
        }
    }

    /**
     * 初始化DaoSession
     */
    private void getDaoSession() {
        if (daoSession == null) {
            daoSession = daoMaster.newSession();
        }
    }

    /**
     * 初始化AsyncDaoSession
     */
    private void getDaoAsyncSession() {
        if (asyncSession == null) {
            getDaoSession();
            asyncSession = daoSession.startAsyncSession();
            asyncSession.setListener(this);
        }
    }

    /**
     * 只关闭helper就好,看源码就知道helper关闭的时候会关闭数据库
     */
    @Override
    public void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }

    }

    @Override
    public void clearDaoSession() {
        if (daoMaster != null) {
            daoMaster = null;
        }
        if (asyncSession != null) {
            asyncSession = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public synchronized void dropDatabase() {
        try {
            openWritableDb();
//            daoSession.deleteAll(AdminDivisionInfo.class);    // clear all elements from a table
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    @Override
    public void insert(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.insert(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.delete(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrReplace(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.insertOrReplace(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.update(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public M selectByPrimaryKey(@NonNull Class<M> entityClass, @NonNull String Id) {
        M m = null;
        try {
            openReadableDb();
            m = daoSession.load(entityClass, Id);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<M> loadAll(@NonNull Class<M> entityClass) {
        List<M> mList = null;
        try {
            openReadableDb();
            mList = daoSession.loadAll(entityClass);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return mList;
    }

    @Override
    public void refresh(@NonNull M m) {
        try {
            openWritableDb();
            daoSession.refresh(m);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runInTx(Runnable runnable) {
        openReadableDb();
        daoSession.runInTx(runnable);
    }
//    @Override
//    public synchronized void bulkInsertPhoneNumbers(Set<DBPhoneNumber> phoneNumbers) {
//        try {
//            if (phoneNumbers != null && phoneNumbers.size() > 0) {
//                openWritableDb();
//                asyncSession.insertOrReplaceInTx(DBPhoneNumber.class, phoneNumbers);
//                assertWaitForCompletion1Sec();
//                daoSession.clear();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
