package mvc.zzy.cusrecyclerview;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by zzy on 16/3/20.
 */
public  interface DataInterface {

     void onRequestSuccess(@NonNull int code,@NonNull String response);
     void onRequestFailure(@NonNull int errCode,@NonNull String errMsg);
     void onLoadModel(@NonNull List<CellModel> list);
}
