package com.SODD.jswipetabs.app;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.SODD.jswipetabs.app.fragments.FragmentOne;
import com.SODD.jswipetabs.app.fragments.FragmentThree;
import com.SODD.jswipetabs.app.fragments.FragmentTwo;


public class Main extends FragmentActivity implements ActionBar.TabListener {

    ActionBar actionBar;
    TabPagerAdapter mTabPagerAdapter;
    ViewPager mViewPager;

    FragmentManager fragmentManager;

    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            fragmentOne = (FragmentOne)fragmentManager.findFragmentByTag(makeFragmentName(R.id.main_pager, 0));
            fragmentTwo = (FragmentTwo)fragmentManager.findFragmentByTag(makeFragmentName(R.id.main_pager, 1));
            fragmentThree = (FragmentThree)fragmentManager.findFragmentByTag(makeFragmentName(R.id.main_pager, 2));
        } else {
            fragmentOne = new FragmentOne();
            fragmentManager.beginTransaction().add(R.id.main_pager, fragmentOne, makeFragmentName(R.id.main_pager, 0)).commit();

            fragmentTwo = new FragmentTwo();
            fragmentManager.beginTransaction().add(R.id.main_pager, fragmentTwo, makeFragmentName(R.id.main_pager, 1)).commit();

            fragmentThree = new FragmentThree();
            fragmentManager.beginTransaction().add(R.id.main_pager, fragmentThree, makeFragmentName(R.id.main_pager, 2)).commit();
        }

        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        mViewPager.setAdapter(mTabPagerAdapter);

        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    actionBar.setSelectedNavigationItem(position);
                }
            });

            for (int i = 0; i < mTabPagerAdapter.getCount(); i++) {
                actionBar.addTab(actionBar.newTab()
                        .setText(mTabPagerAdapter.getPageTitle(i))
                        .setTabListener(this));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {
        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragmentOne;
                case 1:
                    return fragmentTwo;
                case 2:
                    return fragmentThree;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.tab_one_title);
                case 1:
                    return getResources().getString(R.string.tab_two_title);
                case 2:
                    return getResources().getString(R.string.tab_three_title);
            }
            return null;
        }
    }

    private static String makeFragmentName(int viewId, int index) {//special magic from Stack Overflow
        return "android:switcher:" + viewId + ":" + index;
    }
}
