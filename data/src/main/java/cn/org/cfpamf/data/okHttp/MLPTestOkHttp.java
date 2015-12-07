package cn.org.cfpamf.data.okHttp;

import android.content.Context;
import android.os.Bundle;

import cn.org.cfpamf.data.response.Test;

/**
 * Created by zzy on 15/12/3.
 */
public class MLPTestOkHttp extends AbstractMLPGetOkHttp<Test> {

    public static final String TYPE_MLP_TEST = "MLPTestOkHttp";

    public MLPTestOkHttp(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void onMlpSuccess(Test test) {

    }

    @Override
    public AbstractBaseOkHttp getPostEvent() {
        return this;
    }

    @Override
    public String getUrl() {
        return null;
    }
}
