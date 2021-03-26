package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.recyclerview.widget.RecyclerView;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView mMsgTextView;

    public ViewHolder(View itemView) {
        super(itemView);
        if (itemView instanceof TextView) {
            mMsgTextView = (TextView) itemView;
        }
    }

    public TextView getMsgTextView() {
        return mMsgTextView;
    }
}
