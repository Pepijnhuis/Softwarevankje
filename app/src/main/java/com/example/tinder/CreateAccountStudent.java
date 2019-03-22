package com.example.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountStudent extends AppCompatActivity implements CA1Fragment.FragmentCA1Listener, CA3StudentFragment.FragmentCA3StudentListener {

    private CA1Fragment fragmentCA1;



    //This is a FragmentPageAdapter derivative, which will keep every loaded fragment in memory
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //ViewPager will host the section contents
    private ViewPager mViewPager;

    private FirebaseAuth mAuth;
    //Showing the right fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_student);


        mAuth = FirebaseAuth.getInstance();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        fragmentCA1 = new CA1Fragment();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerCreateAccountStudent);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsCreateAccountStudent);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
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
            switch (position){
                case 0:
                    fragment = new CA1Fragment();
                    break; //leave the switch statement
                case 1:
                    fragment = new CA3StudentFragment();
                    break;
                case 2:
                    fragment = new CA4StudentFragment();
                    break;
                case 3:
                    fragment = new CA5StudentFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }

    @Override
    public void onInputCA1Sent(String Email, String Password) {
        Log.d("Debug", "oninput sent called");
        Log.d("Debug",Email);
        Log.d("Debug",Password);
        mAuth= FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(CreateAccountStudent.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(CreateAccountStudent.this, "Signin Error", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Debug", "Signup succesfullll!!!!!");

                }
            }
        });
    }


    @Override
    public void onInputCA3StudentSent() {

        private void getUserInfo() {

            mStudentAccountDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        if (map.get("Name") != null) {
                            NameStudent = map.get("Name").toString();
                            mNameField.setText(NameStudent);
                        }

                        if (map.get("Adress") != null) {
                            AdressStudent = map.get("Adress").toString();
                            mAdressField.setText(AdressStudent);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        private void saveUserInformation() {
            NameStudent = mNameField.getText().toString();
            AdressStudent = mAdressField.getText().toString();

            Map userInfo = new HashMap();
            userInfo.put("Name", NameStudent);
            userInfo.put("Adress", AdressStudent);
            mStudentAccountDatabase.updateChildren(userInfo);

        }



    }
    //Main navigation button
    public void goToMainNavigation(View view) {
        Intent intent = new Intent (CreateAccountStudent.this, MainNavigation.class);
        startActivity(intent);
        return;
    }


}
