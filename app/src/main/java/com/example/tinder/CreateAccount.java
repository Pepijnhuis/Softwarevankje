package com.example.tinder;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class CreateAccount extends AppCompatActivity implements CA2Fragment.FragmentCA2listener {

    //This is a FragmentPageAdapter derivative, which will keep every loaded fragment in memory
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //ViewPager will host the section contents
    private ViewPager mViewPager;

    private String choice;

    private CA2Fragment ca2Fragment;
    private CA3Fragment ca3Fragment;

    //Showing the right fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCreateAccount);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerCreateAccount);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsCreateAccount);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //communication between fragments
        ca2Fragment = new CA2Fragment();
        ca3Fragment = new CA3Fragment();
    }


    //communication between fragments
    @Override
    public void onInputCA2Sent(String input){
        choice = input;
        Log.d("debug", choice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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
    public static class PlaceholderFragment extends Fragment {
        //The fragment argument representing the section number for this fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null; //setting the fragment to null initially
            if (choice == "House") {
                switch (position) {
                    case 0:
                        fragment = new CA1Fragment();
                        break; //leave the switch statement
                    case 1:
                        fragment = new CA2Fragment();
                        break;
                    case 2:
                        fragment = new CA3StudentFragment();
                        break;
                    case 3:
                        fragment = new CA4StudentFragment();
                        break;
                    case 4:
                        fragment = new CA5StudentFragment();
                        break;
                }
            }
                else {
                    switch (position){
                        case 0:
                            fragment = new CA1Fragment();
                            break; //leave the switch statement
                        case 1:
                            fragment = new CA2Fragment();
                            break;
                        case 2:
                            fragment = new CA3HouseFragment();
                            break;
                        case 3:
                            fragment = new CA4HouseFragment();
                            break;
                        case 4:
                            fragment = new CA5HouseFragment();
                            break;
                }

            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }
    }
}
