package com.ellen.modlocal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ellen.libcommon.base.adapter.recyclerview.BaseSingleRecyclerViewAdapter;
import com.ellen.libcommon.base.adapter.recyclerview.BaseViewHolder;
import com.ellen.libcommon.util.ContentProviderUtils;
import com.ellen.modlocal.R;

import java.util.List;

public class LocalMusicAdapter extends BaseSingleRecyclerViewAdapter<ContentProviderUtils.Music, LocalMusicAdapter.LocalMusicViewHolder> {


    public LocalMusicAdapter(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_local_music;
    }

    @Override
    protected LocalMusicViewHolder getNewViewHolder(View view) {
        return new LocalMusicViewHolder(view);
    }

    @Override
    protected void showData(LocalMusicViewHolder localMusicViewHolder, ContentProviderUtils.Music data, int position) {
        localMusicViewHolder.tvMusicName.setText(data.getName());
    }



    static class LocalMusicViewHolder extends BaseViewHolder{
        TextView tvMusicName;
        public LocalMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMusicName = findViewById(R.id.tv_music_name);
        }
    }

}
