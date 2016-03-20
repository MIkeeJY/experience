package cn.org.cfpamf.zhnx;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;


import cn.org.cfpamf.data.manager.StartServiceManager;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;
import cn.org.cfpamf.data.sql.db.Baidu;
import cn.org.cfpamf.data.util.ToastUtils;
import cn.org.cfpamf.zhnx.databinding.ActivityMainBinding;
import de.greenrobot.event.EventBus;

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
     * 处理失败信息
     *
     * @param baiduTestOkHttp
     */
    public void onEventMainThread(BaiduTestOkHttp baiduTestOkHttp) {
        ToastUtils.showError(baiduTestOkHttp.getErrorMessage(), getApplicationContext());
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


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
