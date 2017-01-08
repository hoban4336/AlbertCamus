package com.pikitori.example.albertcamus.ui;

import android.os.Bundle;

import com.pikitori.example.albertcamus.R;
import com.pikitori.example.albertcamus.ui.tabs.Tab1Fragment;
import com.pikitori.example.albertcamus.ui.tabs.Tab2Fragment;
import com.pikitori.example.albertcamus.ui.tabs.UserListFragment;

/**
 * Created by yeon on 2016-12-28.
 */
public class MainTabsConfig {


    public static final class TABINDEX{
        public static final int JSONINFO = 1;
        public static final int USERLIST = 2;
        public static final int HTTPGET = 3;

        public static final int FIRST = 0;
        public static final int LAST = TAB_INFOS.length;
    }

    private static final TabInfo[] TAB_INFOS = {
            new TabInfo("json 정보", R.drawable.ic_online, R.drawable.ic_online_selected, Tab1Fragment.class, null),
            new TabInfo("유저 리스트", R.drawable.ic_chat, R.drawable.ic_chat_selected, UserListFragment.class, null),
            new TabInfo("HTTP GET", R.drawable.ic_chat, R.drawable.ic_chat_selected, Tab2Fragment.class, null)
    };

    public static final int COUNT_TABS(){
        return TAB_INFOS.length;
    }

//    탭의 정보 제공(클래스 TabInfo 의 객체 정보)
    public static final TabInfo TABINFO(int index){
        return (index < 0 || index >= TAB_INFOS.length)? null : TAB_INFOS[index];
    }
    public static class TabInfo {

        public final String tag;
        public final int drawableNormal;
        public final int drawableSelected;
        public final Class<?> klass;
        public final Bundle bundle;

        TabInfo(String tag, int drawableNormal, int drawableSelected, Class<?> klass, Bundle bundle) {
            this.tag = tag;
            this.drawableNormal = drawableNormal;
            this.drawableSelected = drawableSelected;
            this.klass = klass;
            this.bundle = bundle;
        }

    }

}
