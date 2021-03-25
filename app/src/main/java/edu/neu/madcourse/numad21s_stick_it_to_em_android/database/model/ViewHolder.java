package edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView sentItem;

    public ViewHolder(View itemView) {
        super(itemView);
        sentItem = itemView.findViewById(R.id.sentItem);
    }
}
