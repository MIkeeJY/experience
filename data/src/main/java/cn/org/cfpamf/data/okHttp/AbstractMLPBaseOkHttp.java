package cn.org.cfpamf.data.okHttp;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

import cn.org.cfpamf.data.exception.e.ServerResponseException;
import cn.org.cfpamf.data.response.base.BaseServerResponse;
import okhttp3.Response;


/**
 * Created by zzy on 15/9/19.
 * 抽象出基于服务端统一response处理
 */
public abstract class AbstractMLPBaseOkHttp<T extends BaseServerResponse> extends AbstractBaseOkHttp {


    @Override
    public void onSuccess(@NonNull Response response) {
        try {
            String responseString = response.body().string();
            T t = new Gson().fromJson(responseString, getClassT());
            if (Boolean.valueOf(t.getSuccess())) {
                //处理成功消息
                onMlpSuccess(t);
                Logger.d("responseString==" + responseString);
            } else {
                onFailed(new ServerResponseException(t.getResponseStatus().getMessage()));
            }
        } catch (Exception e) {
            onFailed(e);
        }
    }


    /**
     * 子类只处理成功
     *
     * @param t
     */
    public abstract void onMlpSuccess(T t);

    public abstract Type getClassT();
}
