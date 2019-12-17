package com.ellen.libcommon.util.ActivityLifeListener;

import androidx.fragment.app.FragmentActivity;

public class ActivityLifeListenerManager {

    public void startActivityLifeListener(FragmentActivity activity, ActivityLifeListener activityLifeListener){
        LifeFragment lifeFragment = new LifeFragment();
        lifeFragment.setActivityLifeListener(activityLifeListener);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(lifeFragment, LifeFragment.class.getName())
                .commitAllowingStateLoss();
    }
}
