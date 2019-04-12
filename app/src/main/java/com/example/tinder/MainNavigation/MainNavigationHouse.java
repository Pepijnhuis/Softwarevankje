package com.example.tinder.MainNavigation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tinder.Chat.ChatActivity;
import com.example.tinder.Matches.MessagesFragment;
import com.example.tinder.R;

public class MainNavigationHouse extends AppCompatActivity {

    //This is a FragmentPageAdapter derivative, which will keep every loaded fragment in memory
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //ViewPager will host the section contents
    private ViewPager mViewPager;

    //Showing the right fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerMainNavigation);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsMainNavigation);

        //tab colour switch
        TabLayout.Tab tabSettings = tabLayout.getTabAt(0);
        tabSettings.setIcon(R.drawable.selector_settings);

        TabLayout.Tab tabHome = tabLayout.getTabAt(1);
        tabHome.setIcon(R.drawable.selector_home);

        TabLayout.Tab tabMessages = tabLayout.getTabAt(2);
        tabMessages.setIcon(R.drawable.selector_messages);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //A FragmentPageAdapter that returns a fragment corresponding to one of the tabs
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null; //setting the fragment to null initially
            switch (position){
                case 0:
                    fragment = new SettingsFragment();
                    break; //leave the switch statement
                case 1:
                    fragment = new SlideFragmentHouse();
                    break;
                case 2:
                    fragment = new MessagesFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    //ChatActivity button
    public void goToChat(View view) {
        Intent intent = new Intent(MainNavigationHouse.this, ChatActivity.class);
        startActivity(intent);
        return;
    }
}
