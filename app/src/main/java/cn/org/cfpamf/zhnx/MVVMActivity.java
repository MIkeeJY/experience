package cn.org.cfpamf.zhnx;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.org.cfpamf.data.manager.StartServiceManager;
import cn.org.cfpamf.data.sql.db.Baidu;
import cn.org.cfpamf.zhnx.databinding.ActivityMainBinding;

public class MVVMActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);
        StartServiceManager.startBaiduOkHttp(this);
    }

    /**
     * 处理信息
     *
     * @param baidu
     */
    @Subscribe
    public void onEventBaidu(Baidu baidu) {
        //更新UI
        activityMainBinding.setBaidu(baidu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
