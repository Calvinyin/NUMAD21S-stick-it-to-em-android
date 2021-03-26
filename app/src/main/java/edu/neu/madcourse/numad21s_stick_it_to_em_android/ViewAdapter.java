package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    protected List<String> mItemList;

    public ViewAdapter(Context mContext, List<String> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View textView = LayoutInflater
                .from(mContext)
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.getMsgTextView().setText(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setmItemList(List<String> itemList) {
        mItemList = itemList;
    }
}
