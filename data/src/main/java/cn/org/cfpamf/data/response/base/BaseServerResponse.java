package cn.org.cfpamf.data.response.base;

import android.os.Parcel;
import android.os.Parcelable;

import cn.org.cfpamf.data.i.IOkHttpResponse;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/15 17:02
 * 修改人：Administrator
 * 修改时间：2015/9/15 17:02
 * 修改备注：
 */
public class BaseServerResponse implements IOkHttpResponse {

    private String success;
    private ResponseStatus responseStatus;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @Override
    public boolean isSuccess() {
        return Boolean.valueOf(this.success);
    }

    @Override
    public String getErrorMessage() {
        return responseStatus.getMessage();
    }
}
