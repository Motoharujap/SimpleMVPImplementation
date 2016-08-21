package ru.maslov.sandbox.lists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Администратор on 21.08.2016.
 */
public abstract class BaseRecyclerViewAdapter<T>  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected Context mContext;
    private ArrayList<T> mItems = new ArrayList<>();
    private View.OnClickListener mItemClickListener;

    public BaseRecyclerViewAdapter(Context context){
        mContext = context;
    }
    public void setItems(ArrayList<T> items){
        mItems.clear();
        mItems.addAll(items);
    }
    public Object getItem(int position) {
        return mItems.get(position);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener){
        mItemClickListener = itemClickListener;
    }

    protected class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(mItemClickListener);
            ButterKnife.bind(this, itemView);
        }
    }
}
