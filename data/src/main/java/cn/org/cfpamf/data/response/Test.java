package cn.org.cfpamf.data.response;

import android.os.Parcel;

import cn.org.cfpamf.data.response.base.BaseServerResponse;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 10:48
 * 修改人：Administrator
 * 修改时间：2015/11/10 10:48
 * 修改备注：
 */
public class Test extends BaseServerResponse {

    protected Test(Parcel in) {
    }
    public static final Creator<BaseServerResponse> CREATOR = new Creator<BaseServerResponse>() {
        public BaseServerResponse createFromParcel(Parcel source) {
            return new Test(source);
        }

        public BaseServerResponse[] newArray(int size) {
            return new BaseServerResponse[size];
        }
    };

}
