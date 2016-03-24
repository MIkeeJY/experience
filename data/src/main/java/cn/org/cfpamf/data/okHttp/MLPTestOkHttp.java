package cn.org.cfpamf.data.okHttp;

import java.lang.reflect.Type;

import cn.org.cfpamf.data.response.Test;

/**
 * Created by zzy on 15/12/3.
 */
public class MLPTestOkHttp extends AbstractMLPGetOkHttp<Test> {

    @Override
    public void onMlpSuccess(Test test) {

    }

    @Override
    public Type getClassT() {
        return Test.class;
    }


    @Override
    public String getUrl() {
        return null;
    }

}
