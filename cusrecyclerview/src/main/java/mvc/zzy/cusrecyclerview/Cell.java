package mvc.zzy.cusrecyclerview;

import android.view.View;

/**
 * Created by zzy on 16/3/20.
 */
public interface Cell {
    void initView(View view);

    void setModel(CellModel model);

    void layoutSubviews();
}
