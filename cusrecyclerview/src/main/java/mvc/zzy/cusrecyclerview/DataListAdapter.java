package mvc.zzy.cusrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zzy on 16/3/20.
 */
public class DataListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    public List<CellModel> mList = null;

    public DataListAdapter(List<CellModel> mList) {
        setHasStableIds(true);
        this.mList = mList;
    }

    protected AdapterInterface adapterInterface;


    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }


    /**
     * click listener
     */
    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View view, int position);
    }

    /**
     * set a long click listener
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * set a click listener
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    /**
     * bind click listner to itemview
     *
     * @param vh       viewholder
     * @param position position
     */
    protected final void bindItemViewClickListener(BaseViewHolder vh, final int position) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(view, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        try {
            final CellModel item = getItem(position);
            holder.itemView.setTag(item);
            bindItemViewClickListener(holder, position);
            Cell cell = holder.cell;
            cell.setModel(item);
            cell.layoutSubviews();
        } catch (Exception e) {
            if (adapterInterface != null) {
                adapterInterface.getException(e);
            }
        }

    }

    /**
     * long click listener
     */
    protected OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        try {
            CellModel cellModel = mList.get(viewType);
            if (cellModel == null) {
                throw new NullPointerException("CellModel can not be null");
            }
            viewHolder = new BaseViewHolder(inflateItemView(parent, cellModel.layout), cellModel.cell);
        } catch (Exception e) {
            if (adapterInterface != null) {
                adapterInterface.getException(e);
            }
        }
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public CellModel getItem(int position) {
        return mList.get(position);
    }


    /**
     * inflate a itemView by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a itemView by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    public void add(CellModel object) {
        mList.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, CellModel object) {
        mList.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends CellModel> collection) {
        if (collection != null) {
            mList.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void replace(int index, CellModel object) {
        mList.remove(index);
        mList.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(CellModel... mList) {
        addAll(Arrays.asList(mList));
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public boolean contains(CellModel object) {
        if (mList.contains(object)) {
            return true;
        }
        return false;
    }

    public void remove(CellModel object) {
        mList.remove(object);
        notifyDataSetChanged();
    }

    public List<CellModel> getAll() {
        return mList;
    }

}
