package cn.org.cfpamf.data.factory;

import android.content.Context;
import android.os.Bundle;

import cn.org.cfpamf.data.okHttp.AbstractBaseOkHttp;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 10:44
 * 修改人：Administrator
 * 修改时间：2015/11/10 10:44
 * 修改备注：
 */
public class OkHttpFactory {

    private static AbstractBaseOkHttp abstractBaseOkHttp;

    public static AbstractBaseOkHttp createHttp(Context context, String className, Bundle bundle) {

        try {
            abstractBaseOkHttp = (AbstractBaseOkHttp) Class.forName(className).newInstance();
            abstractBaseOkHttp.setBundle(bundle);
            abstractBaseOkHttp.setContext(context);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return abstractBaseOkHttp;
    }
}
