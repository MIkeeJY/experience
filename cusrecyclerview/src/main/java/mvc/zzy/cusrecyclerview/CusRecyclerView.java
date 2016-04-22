package mvc.zzy.cusrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zzy on 16/3/20.
 */
public class CusRecyclerView extends RecyclerView {

    public DataSources mDataResources;

    public CusRecyclerView(Context context) {
        super(context);
    }

    public CusRecyclerView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void setDataResources(@NonNull DataSources dataResources) {
        if (dataResources == null) {
            throw new NullPointerException("DataSources can not be null");
        }
        this.mDataResources = dataResources;
        this.setAdapter(mDataResources.getDataListAdapter());
    }

}
