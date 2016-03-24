package cn.org.cfpamf.data.i;



import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 10:14
 * 修改人：Administrator
 * 修改时间：2015/10/16 10:14
 * 修改备注：
 */
public interface IOkHttpRequest {
    /**
     * 获取HttpClient
     * @return
     */
    OkHttpClient getOkHttpClient();

    /**
     * 配置地址
     * @return
     */
    String getUrl();

    /**
     * 配置请求
     * @return
     */
    Request getRequest();

    /**
     * 配置请求
     * @return
     */
    Request.Builder getRequestBuilder();

    /**
     * 处理返回消息
     * @return
     */
    Callback getResponseCallBack();

    /**
     * 获取请求主体
     * @return
     */
    RequestBody getRequestBody();

    /**
     * 启动网络请求
     */
    void execute();
}
