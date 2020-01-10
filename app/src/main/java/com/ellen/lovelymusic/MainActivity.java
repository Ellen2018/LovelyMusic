package com.ellen.lovelymusic;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ellen.libautoview.musicplay.MusicPlayCallback;
import com.ellen.libautoview.musicplay.MusicPlayDataSetting;
import com.ellen.libautoview.musicplay.MusicPlayView;
import com.ellen.libcommon.base.BaseActivity;
import com.ellen.libcommon.base.adapter.viewpager.SaveStatusFragmentPagerAdapter;
import com.ellen.libcommon.util.ContentProviderUtils;
import com.ellen.libcommon.util.PermissionUtils;
import com.ellen.libcommon.util.ToastUtils;
import com.ellen.libcommon.util.statusutil.StatusUtils;
import com.ellen.lovelymusic.mediaplay.MusicPlaySetting;
import com.ellen.lovelymusic.mediaplay.MusicPlayer;
import com.ellen.modlocal.LocalFragment;
import com.ellen.modme.MeFragment;
import com.ellen.modnet.NetFragment;
import com.ellen.supermessagelibrary.BaseEvent;
import com.ellen.supermessagelibrary.MessageEventTrigger;
import com.ellen.supermessagelibrary.MessageManager;
import com.ellen.supermessagelibrary.SuperMessage;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseActivity.ButterKnifeInterface {

    @BindView(R.id.iv_left_menu)
    ImageView ivLeftMenu;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_menu_1)
    TextView tvMenu1;
    @BindView(R.id.tv_menu_2)
    TextView tvMenu2;
    @BindView(R.id.tv_menu_3)
    TextView tvMenu3;
    @BindView(R.id.music_play_view)
    MusicPlayView<ContentProviderUtils.Music> musicMusicPlayView;

    private PermissionUtils permissionUtils;

    MusicPlayDataSetting<ContentProviderUtils.Music> musicMusicPlayDataSetting = new MusicPlayDataSetting<ContentProviderUtils.Music>() {
        @Override
        public String getSingerName(ContentProviderUtils.Music music) {
            return music.getArtist();
        }

        @Override
        public String getMusicName(ContentProviderUtils.Music music) {
            return music.getName();
        }

        @Override
        public long getMusicTime(ContentProviderUtils.Music music) {
            return music.getDuration();
        }

        @Override
        public String getPath(ContentProviderUtils.Music music) {
            return music.getPath();
        }
    };

    private Fragment currentFragment;
    private List<Fragment> fragmentList;
    private SaveStatusFragmentPagerAdapter saveStatusFragmentPagerAdapter;
    private BaseEvent baseEvent;

    @OnClick({R.id.tv_menu_1, R.id.tv_menu_2, R.id.tv_menu_3})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_menu_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_menu_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_menu_3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private final int MOD_LOCAL = 0, MOD_NET = 1, MOD_ME = 2;

    @Override
    protected void setStatus() {
        StatusUtils.setNoActionBar(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        musicMusicPlayView.setMusicPlayCallback(new MusicPlayCallback() {
            @Override
            public void clickPlay() {
                ToastUtils.toast(getContext(), "点击了播放");
            }

            @Override
            public void prePiece() {
                ToastUtils.toast(getContext(), "点击了上一曲");
            }

            @Override
            public void nextPiece() {
                MusicPlayer.getInstance().next();
            }

            @Override
            public void clickMusicList() {
                ToastUtils.toast(getContext(), "点击了播放列表");
            }

            @Override
            public void playPosition(int position) {
                permissionUtils = new PermissionUtils(getActivity());
                permissionUtils.startCheckFileReadWritePermission(1, new PermissionUtils.PermissionCallback() {
                    @Override
                    public void success() {
                        ContentProviderUtils.Music music = musicMusicPlayDataSetting.getDataList().get(position);
                        //播放音乐
                        MusicPlaySetting<ContentProviderUtils.Music> musicMusicPlaySetting = new MusicPlaySetting<ContentProviderUtils.Music>() {
                            @Override
                            public String getPlayPath(ContentProviderUtils.Music music) {
                                return music.getPath();
                            }
                        };
                        musicMusicPlaySetting.setCurrentPostion(position);
                        musicMusicPlaySetting.setDataList(musicMusicPlayDataSetting.getDataList());
                        MusicPlayer.getInstance().setMusicPlaySetting(musicMusicPlaySetting);
                    }

                    @Override
                    public void failure() {
                        ToastUtils.toast(getContext(), "无法播放");
                    }
                });

            }

            @Override
            public void clickMusicPlayer() {
                ToastUtils.toast(getContext(), "点击了播放面板");
            }
        });
    }

    @Override
    protected void initData() {
        tvMenu1.setText("本地");
        tvMenu2.setText("网络");
        tvMenu3.setText("我");
        fragmentList = new ArrayList<>();
        fragmentList.add(new LocalFragment());
        fragmentList.add(new NetFragment());
        fragmentList.add(new MeFragment());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case MOD_LOCAL:
                        tvMenu1.setTextColor(Color.RED);
                        tvMenu2.setTextColor(Color.BLACK);
                        tvMenu3.setTextColor(Color.BLACK);
                        break;
                    case MOD_NET:
                        tvMenu1.setTextColor(Color.BLACK);
                        tvMenu2.setTextColor(Color.RED);
                        tvMenu3.setTextColor(Color.BLACK);
                        break;
                    case MOD_ME:
                        tvMenu1.setTextColor(Color.BLACK);
                        tvMenu2.setTextColor(Color.BLACK);
                        tvMenu3.setTextColor(Color.RED);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        saveStatusFragmentPagerAdapter = new SaveStatusFragmentPagerAdapter(viewPager, getActivity(), fragmentList);
        currentFragment = fragmentList.get(0);
        tvMenu1.setTextColor(Color.RED);
        tvMenu2.setTextColor(Color.BLACK);
        tvMenu3.setTextColor(Color.BLACK);
        MessageManager.getInstance().registerMessageEvent("play", baseEvent = new MessageEventTrigger() {
            @Override
            public void handleMessage(SuperMessage message) {
                List<ContentProviderUtils.Music> musicList = (List<ContentProviderUtils.Music>) message.object;
                musicMusicPlayDataSetting.setDataList(musicList);
                musicMusicPlayDataSetting.setCurrentPlayPosition(message.what);
                musicMusicPlayView.setMusicPlayDataSetting(getActivity(), musicMusicPlayDataSetting);
            }
        });
    }

    @Override
    protected void destory() {
        MessageManager.getInstance().registerMessageEvent("play", baseEvent);
    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return true;
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionUtils != null)
            permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (currentFragment != null)
            currentFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
