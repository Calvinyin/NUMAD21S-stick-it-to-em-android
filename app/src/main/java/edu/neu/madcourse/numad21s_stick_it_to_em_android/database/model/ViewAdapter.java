package edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.R;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private final ArrayList<String> itemList;

    public ViewAdapter(Context context, ArrayList<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.sentItem.setText(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
