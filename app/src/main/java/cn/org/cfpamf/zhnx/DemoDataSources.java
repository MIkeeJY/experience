package cn.org.cfpamf.zhnx;


import java.util.List;

import mvc.zzy.cusrecyclerview.CellModel;
import mvc.zzy.cusrecyclerview.DataSources;

/**
 * Created by zzy on 16/3/20.
 */
public class DemoDataSources extends DataSources {

    @Override
    public void onRequestSuccess(int code, String response) {

    }

    @Override
    public void onRequestFailure(int errCode, String errMsg) {

    }

    @Override
    public void onLoadModel(List<CellModel> list) {

        for (int i = 0; i < 10; i++) {
            DemoCellModel demoCellModel = new DemoCellModel();
            demoCellModel.cell = new DemoCell();
            if (i % 2 == 0) {
                demoCellModel.layout = R.layout.cell;
                demoCellModel.text = "测试" + i;
            } else {
                demoCellModel.layout = R.layout.cellt;
                demoCellModel.text = "跳转至MVVM模式Activity";
            }

            list.add(demoCellModel);
        }

    }


}
