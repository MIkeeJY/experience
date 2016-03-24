package cn.org.cfpamf.data.okHttp;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import cn.org.cfpamf.data.sql.BaiduDbManager;
import cn.org.cfpamf.data.sql.db.Baidu;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 11:00
 * 修改人：Administrator
 * 修改时间：2015/11/10 11:00
 * 修改备注：
 */
public class BaiduTestOkHttp extends AbstractBaseOkHttp {


    @Override
    public String getUrl() {
        return "Http://www.baidu.com";
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).build();
    }

    /**
     * 没有body
     *
     * @return
     */
    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public void onSuccess(@NonNull Response response) {
        try {
            String strResponse = response.body().string();
            Baidu baidu = new Baidu();
            baidu.setId(UUID.randomUUID().toString());
            baidu.setResponse(strResponse);
            //插入数据库
            boolean success = new BaiduDbManager().insert(baidu);
            if (success) {
                //通知前台更新
                EventBus.getDefault().post(baidu);
            } else {
                onFailed(new SQLException(strResponse));
            }
            Logger.d(strResponse);
        } catch (IOException e) {
            onFailed(e);
        }
    }

}
