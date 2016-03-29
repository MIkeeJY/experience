package cn.org.cfpamf.data.exception.e;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 14:54
 * 修改人：Administrator
 * 修改时间：2015/10/16 14:54
 * 修改备注：
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }
    public static String createMessage(Exception e) {
        if (e instanceof SocketTimeoutException) {
            return "网络连接超时，请检查网络";
        } else if (e instanceof ConnectException) {
            return "请求地址无效";
        } else if (e instanceof ServerResponseException) {
            return e.getMessage();
        }
        return e.toString();
    }
}
