package mvc.zzy.cusrecyclerview;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by zzy on 16/3/20.
 */
public interface Cell {
    void initView(@NonNull View view);

    void setModel(@NonNull CellModel model);

    void layoutSubviews();
}
