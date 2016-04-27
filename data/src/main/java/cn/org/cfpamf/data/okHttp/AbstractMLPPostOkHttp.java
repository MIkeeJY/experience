package cn.org.cfpamf.data.okHttp;


import com.google.gson.Gson;

import cn.org.cfpamf.data.i.IOkHttpResponse;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 10:57
 * 修改人：Administrator
 * 修改时间：2015/10/16 10:57
 * 修改备注：
 */
public abstract class AbstractMLPPostOkHttp<T extends IOkHttpResponse> extends AbstractMLPBaseOkHttp<T> {

    protected static final MediaType JSON = MediaType.parse(HttpConstant.CONTENT_TYPE);

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).post(getRequestBody()).build();
    }

    @Override
    public RequestBody getRequestBody() {
        requestJson = new Gson().toJson(bundle.getParcelable(BUNDLE_POST_OR_PUT_KEY));
        return RequestBody.create(JSON, requestJson);
    }
}
