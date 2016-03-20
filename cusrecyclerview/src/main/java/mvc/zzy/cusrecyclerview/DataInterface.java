package mvc.zzy.cusrecyclerview;

import java.util.List;

/**
 * Created by zzy on 16/3/20.
 */
public  interface DataInterface {

     void onRequestSuccess(int code, String response);
     void onRequestFailure(int errCode, String errMsg);
     void onLoadModel(List<CellModel> list);
}
