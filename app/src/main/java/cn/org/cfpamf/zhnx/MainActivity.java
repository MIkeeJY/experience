package cn.org.cfpamf.zhnx;

import android.databinding.DataBindingUtil;


import cn.org.cfpamf.data.base.BaseActivity;
import cn.org.cfpamf.data.manager.StartServiceManager;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;
import cn.org.cfpamf.data.sql.db.Baidu;
import cn.org.cfpamf.zhnx.databinding.ActivityMainBinding;

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
