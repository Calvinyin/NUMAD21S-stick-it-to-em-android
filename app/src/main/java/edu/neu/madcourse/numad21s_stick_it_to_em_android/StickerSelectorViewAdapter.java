package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import android.content.Context;
import android.view.View;

import java.util.List;

public class StickerSelectorViewAdapter extends ViewAdapter {
    private DatabaseController mDatabaseController;

    public StickerSelectorViewAdapter(Context context, List<String> itemList, DatabaseController databaseController) {
        super(context, itemList);
        mDatabaseController = databaseController;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.getMsgTextView().setOnClickListener(new StickerSendOnClickListener(mItemList.get(position), mDatabaseController));
    }

    class StickerSendOnClickListener implements View.OnClickListener {

        private String mSticker;
        private DatabaseController mDatabaseController;

        public StickerSendOnClickListener(String sticker, DatabaseController databaseController) {
            mSticker = sticker;
            mDatabaseController = databaseController;
        }

        @Override
        public void onClick(View v) {
            mDatabaseController.sendSticker(mSticker);
        }
    }
}
