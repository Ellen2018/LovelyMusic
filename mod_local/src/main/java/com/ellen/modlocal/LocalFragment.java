package com.ellen.modlocal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellen.common.base.BaseSecondMvpFragment;
import com.ellen.libcommon.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.ellen.libcommon.base.adapter.recyclerview.BaseViewHolder;
import com.ellen.libcommon.util.ContentProviderUtils;
import com.ellen.libcommon.util.PermissionUtils;
import com.ellen.libcommon.util.ToastUtils;
import com.ellen.modlocal.adapter.LocalMusicAdapter;
import com.ellen.supermessagelibrary.MessageManager;
import com.ellen.supermessagelibrary.SuperMessage;

import java.util.List;

public class LocalFragment extends BaseSecondMvpFragment<LocalPresenter> implements LocalAgreement.ALocalView {

    private RecyclerView recyclerView;

    private PermissionUtils permissionUtils;
    private LocalMusicAdapter localMusicAdapter;
    private boolean isRefresh = false;

    @Override
    protected void initMVP() {
       mFragmentPresenter = new LocalPresenter();
       mFragmentPresenter.mModel = new LocalModel();
       mFragmentPresenter.mView = this;
    }

    @Override
    protected void initData() {
        permissionUtils = new PermissionUtils(getActivity());
        permissionUtils.startCheckFileReadWritePermission(0, new PermissionUtils.PermissionCallback() {
            @Override
            public void success() {
                if(!isRefresh)
                    mFragmentPresenter.getLocalMusics(getActivity());
            }

            @Override
            public void failure() {
                ToastUtils.toast(getActivity(),"读取本地音乐数据失败，你没有开启文件读写权限!");
            }
        });
    }

    @Override
    protected void initView() {
      recyclerView = findViewById(R.id.recycler_view);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_local;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void getLocalMusicSuccess(List<ContentProviderUtils.Music> musicList) {
        ToastUtils.toast(getActivity(),"获取本地音乐数据成功!");
        if(isRefresh){
            //对比数据是否有变化，有变化则刷新
        }else {
            localMusicAdapter = new LocalMusicAdapter(getActivity(), musicList);
            localMusicAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseViewHolder baseViewHolder, int position) {
                    SuperMessage superMessage = new SuperMessage("play");
                    superMessage.object = localMusicAdapter.getDataList();
                    superMessage.what = position;
                    MessageManager.getInstance().sendMessage(superMessage);
                }
            });
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_view,null);
            localMusicAdapter.addHeaderView(view);
            recyclerView.setAdapter(localMusicAdapter);
            isRefresh = true;
        }
    }

    @Override
    public void getLocalMusicFailure(String errMessage) {
        ToastUtils.toast(getActivity(),"加载本地音乐数据失败:"+errMessage);
    }

}
