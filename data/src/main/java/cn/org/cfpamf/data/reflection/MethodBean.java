package cn.org.cfpamf.data.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zzy on 16/3/31.
 */
public class MethodBean {
    private Object target = null;
    private Method method = null;
    private Object[] params = null;

    public MethodBean(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    public void invoke() {
        try {
            method.invoke(target, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
