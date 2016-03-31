package cn.org.cfpamf.data.reflection;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by zzy on 16/3/31.
 */
public class PerformMethodUtil {

    public static final int WhatHandlerMessage = 0;
    private Handler handler = null;

    private static class PerformMethodLoader {
        public static final PerformMethodUtil INSTANCE = new PerformMethodUtil();
    }

    public static PerformMethodUtil getInstance() {
        return PerformMethodLoader.INSTANCE;
    }

    private PerformMethodUtil() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Looper.prepare();
            Log.d(this.getClass().getSimpleName(), "Looper.prepare()");
        }
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == WhatHandlerMessage) {
                    MethodBean bean = (MethodBean) msg.obj;
                    bean.invoke();
                }
                super.handleMessage(msg);
            }
        };
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.d(this.getClass().getSimpleName(), "Looper.loop()");
            Looper.loop();
        }
    }


    /**
     * @param target
     * @param method
     * @param delay
     * @param params
     * @throws NoSuchMethodException
     */
    public void performMethodOnMainThread(Object target, String method, long delay, Object... params) {
        Class[] types = null;
        if (params != null) {
            types = Util.getParamTypes(params);
        }
        try {
            Method m = target.getClass().getMethod(method, types);
            Message message = Message.obtain();
            message.what = WhatHandlerMessage;
            MethodBean methodBean = new MethodBean(target, m, params);
            message.obj = methodBean;
            handler.sendMessageDelayed(message, delay);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void performMethodOnMainThread(Object target, String method, Object... params) {
        performMethodOnMainThread(target, method, 0, params);
    }
}
