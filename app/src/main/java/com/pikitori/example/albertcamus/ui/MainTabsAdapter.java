package com.pikitori.example.albertcamus.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.pikitori.example.albertcamus.R;

public class MainTabsAdapter extends FragmentStatePagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private final Context context;
    private final TabHost tabHost;
    private final ViewPager viewPager;


    private View currentTabView;
    private int currentIndex;

    //FragmentStatePagerAdapter
    public MainTabsAdapter(Context context, TabHost tabHost, ViewPager viewpager) {
        super(((FragmentActivity) context).getSupportFragmentManager());

        //초기값.
        currentTabView = null;
        currentIndex = -1;

        this.context = context;
        this.tabHost = tabHost;
        this.viewPager = viewpager;

        //tabhost 만들기
        this.tabHost.setup();
        this.tabHost.getTabWidget().setDividerDrawable(null);

        //페이지 사이의 간격
//        this.viewPager.setPageMargin(20);
//        viewpager.setPageMarginDrawable(R.color.bg_color_tab);

        //뷰페이저에게 MainTabsAdapter 설정.
        this.viewPager.setAdapter(this);

        //tabhost에게 OnTabChangeListener 설정
        this.tabHost.setOnTabChangedListener(this);

        //viewpager에게 OnClickListener  설정
        this.viewPager.setOnPageChangeListener(this);

        int count = MainTabsConfig.COUNT_TABS();
        //탭의 제목 달기
        for (int i = 0; i < count; i++) {
            MainTabsConfig.TabInfo tabInfo = MainTabsConfig.TABINFO(i);

            View view = LayoutInflater.from(context).inflate(R.layout.tab_background, null);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabInfo.tag).setIndicator(view);
            //Dummy 가 필요한 이유????
            tabSpec.setContent(new DummyTabFactory(this.context));

            this.tabHost.addTab(tabSpec);

//            normal 값이 지정 되어 있으면서, selected 값이 지정되어 있으면
//            if(tabInfo.drawableNormal !=0  && tabInfo.drawableSelected !=0){
//                ImageView imageView = (ImageView) view.findViewById(R.id.tab_image);
//                imageView.setVisibility(View.VISIBLE);
//                imageView.setImageDrawable(this.context.getResources().getDrawable(tabInfo.drawableNormal));
//            } else{
                TextView textview = (TextView) view.findViewById(R.id.tab_text);
                textview.setVisibility(View.VISIBLE);
                textview.setText(tabInfo.tag);
//            }

            notifyDataSetChanged();
        }

    }

    @Override
    public int getItemPosition(Object object) {
//        안쓰는거??
        return PagerAdapter.POSITION_NONE;
    }

//    현재 페이지 위치 설정.
    public void selectTab(int position){
        tabHost.setCurrentTab(position);
    }

    //FragmentStatePagerAdapter
    @Override
    public Fragment getItem(int position) {
        MainTabsConfig.TabInfo tabInfo = MainTabsConfig.TABINFO(position);
        return Fragment.instantiate(context, tabInfo.klass.getName(), tabInfo.bundle);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    //FragmentStatePagerAdapter
    @Override
    public int getCount() {
//        탭의 전체 갯수
        return MainTabsConfig.COUNT_TABS();
    }

    //TabHost.OnTabChangeListener
    @Override
    public void onTabChanged(String tabId) {
        MainTabsConfig.TabInfo tabInfo;

        if(currentTabView !=null && currentIndex >=0) {
            tabInfo = MainTabsConfig.TABINFO(currentIndex);
            if (tabInfo.drawableNormal != 0 && tabInfo.drawableSelected != 0) {
                ((ImageView) currentTabView.findViewById(R.id.tab_image))
                        .setImageDrawable(context.getResources().getDrawable(tabInfo.drawableNormal));
            }
        }

        currentIndex = tabHost.getCurrentTab();
        currentTabView = tabHost.getCurrentTabView();

        tabInfo = MainTabsConfig.TABINFO(currentIndex);
        if(tabInfo.drawableNormal !=0 && tabInfo.drawableSelected !=0){
            ( ( ImageView ) currentTabView.findViewById( R.id.tab_image ) )
                    .setImageDrawable(  context.getResources().getDrawable( tabInfo.drawableSelected) );

        }

        // pager and title
        viewPager.setCurrentItem( currentIndex );
        ( (Activity) context ).setTitle( context.getResources().getString( R.string.app_name ) + " - " + tabInfo.tag );


    }

    //ViewPager.OnPageChangeListener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //ViewPager.OnPageChangeListener
    @Override
    public void onPageSelected(int position) {

        TabWidget widget = tabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();

        widget.setDescendantFocusability( ViewGroup.FOCUS_BLOCK_DESCENDANTS );
        tabHost.setCurrentTab( position );
        widget.setDescendantFocusability( oldFocusability );

        if( position == 0 ) {
        }

    }

    //ViewPager.OnPageChangeListener
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class DummyTabFactory implements TabHost.TabContentFactory {

        private final Context context;

        public DummyTabFactory(Context context) {
            this.context = context;
        }

//        제목 값이 들어오면
        @Override
        public View createTabContent(String tag) {
            Log.d("###Dummy", tag);
            View view = new View(context);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }
}
