package cn.org.cfpamf.zhnx;

import android.databinding.DataBindingUtil;
import android.util.Log;


import cn.org.cfpamf.data.base.BaseActivity;
import cn.org.cfpamf.data.manager.StartServiceManager;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;
import cn.org.cfpamf.data.sql.db.Baidu;
import cn.org.cfpamf.zhnx.databinding.ActivityMainBinding;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void setToolbar() {
    }

    @Override
    protected void setLayoutContentView() {
        activityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void afterView() {
        StartServiceManager.startBaiduOkHttp(this);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "word";
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("rx", s);
                    }
                });

    }

    /**
     * 处理失败信息
     *
     * @param baiduTestOkHttp
     */
    public void onEventMainThread(BaiduTestOkHttp baiduTestOkHttp) {
        createHintDialog("失败", baiduTestOkHttp.getErrorMessage());
    }

    /**
     * 处理成功信息
     *
     * @param baidu
     */
    public void onEventMainThread(Baidu baidu) {
        //更新UI
        activityMainBinding.setBaidu(baidu);
    }
}
