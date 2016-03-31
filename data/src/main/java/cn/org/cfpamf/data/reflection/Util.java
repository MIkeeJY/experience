package cn.org.cfpamf.data.reflection;

/**
 * Created by zzy on 16/3/31.
 */
public class Util {

    private Util() {
        //empty
    }

    /**
     * 获取类类型
     *
     * @param objects
     * @return
     */
    public static Class[] getParamTypes(Object... objects) {
        int length = objects.length;
        Class[] classes = new Class[length];
        for (int i = 0; i < length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }

}
