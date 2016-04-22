package cn.org.cfpamf.zhnx;

import android.view.View;
import android.widget.TextView;

import mvc.zzy.cusrecyclerview.Cell;
import mvc.zzy.cusrecyclerview.CellModel;


/**
 * Created by zzy on 16/3/20.
 */
public class DemoCell implements Cell {

    TextView text;

    @Override
    public void initView(View view) {
        text= (TextView) view.findViewById(R.id.text_app);

    }

    @Override
    public void setModel(CellModel model) {
        DemoCellModel demoCellModel= (DemoCellModel) model;
        text.setText(demoCellModel.text);
    }

    @Override
    public void layoutSubviews() {

    }
}
