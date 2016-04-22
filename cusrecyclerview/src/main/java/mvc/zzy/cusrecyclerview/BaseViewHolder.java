package mvc.zzy.cusrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzy on 16/3/20.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public Cell cell = null;

    public BaseViewHolder(View itemView, Cell cell) {
        super(itemView);
        this.cell = cell;
        if (cell == null)
            throw new NullPointerException("Cell can not be null");
        else
            cell.initView(itemView);
    }
}
