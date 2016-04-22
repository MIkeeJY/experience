package mvc.zzy.cusrecyclerview;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzy on 16/3/20.
 */
public abstract class DataSources implements DataInterface, AdapterInterface {

    private List<CellModel> mModelList;
    private DataListAdapter mDataListAdapter;

    public DataSources() {
        init();
    }

    private void init() {
        mModelList = new ArrayList<>();
        mDataListAdapter = new DataListAdapter(mModelList);
        mDataListAdapter.setAdapterInterface(this);
        onLoadModel(mModelList);
    }

    public void addItem(@NonNull CellModel cellModel) {
        mDataListAdapter.add(cellModel);
    }


    public DataListAdapter getDataListAdapter() {
        return this.mDataListAdapter;
    }


    @Override
    public void getException(Exception e) {
        Log.e(this.getClass().getSimpleName(), e.toString());
    }
}
