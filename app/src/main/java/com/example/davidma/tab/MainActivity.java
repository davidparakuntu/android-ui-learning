package com.example.davidma.tab;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class MainActivity extends FragmentActivity{

    private TabLayout tab;

    private Stack<String> trigger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tab);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        trigger = new Stack<>();
        LocalFragmentPagerAdapter adapter = new LocalFragmentPagerAdapter(getSupportFragmentManager());
        final ViewPager pager = findViewById(R.id.viewPager5);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //tab.setScrollPosition(position,0,true);
               // tab.scrollTo(positionOffsetPixels,0);

            }
            @Override
            public void onPageSelected(int position) {
                //tab.setScrollPosition(position,0,true);
                tab.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                pager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                pager.setCurrentItem(position);
            }
        });


    }


    public class LocalFragmentPagerAdapter extends FragmentPagerAdapter{

        public LocalFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LocalFragment.instance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
