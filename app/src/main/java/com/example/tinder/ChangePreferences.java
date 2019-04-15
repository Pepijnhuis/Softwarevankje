package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tinder.MainNavigation.MainNavigationStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePreferences extends AppCompatActivity {

    private Button mButtonBackChangeInformation, mButtonSafeInformation;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private EditText mMinRent, mMaxRent, mMinSize,mMaxSize,mMinHouseMates,mMaxHouseMates;
    private String MinRent, MaxRent, MinSize, MaxSize, MinHouseMates, MaxHouseMates, userId;
    private String Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_preferences);

        mMinRent = (EditText) findViewById(R.id.MinRent);
        mMaxRent = (EditText) findViewById(R.id.MaxRent);
        mMinSize = (EditText) findViewById(R.id.MinSize);
        mMaxSize = (EditText) findViewById(R.id.MaxSize);
        mMinHouseMates = (EditText) findViewById(R.id.MinHousemates);
        mMaxHouseMates = (EditText) findViewById(R.id.MaxHousemates);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        getUserInfo();
    }

    private void getUserInfo() {
        mStudentAccountDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("MinRent")!=null){
                        MinRent = map.get("MinRent").toString();
                        mMinRent.setText(MinRent);
                    }
                    if(map.get("MaxRent")!=null){
                        MaxRent = map.get("MaxRent").toString();
                        mMaxRent.setText(MaxRent);
                    }
                    if(map.get("MinSize")!=null){
                        MinSize = map.get("MinSize").toString();
                        mMinSize.setText(MinSize);
                    }
                    if(map.get("MaxSize")!=null){
                        MaxSize = map.get("MaxSize").toString();
                        mMaxSize.setText(MaxSize);
                    }
                    if(map.get("MinHouseMates")!=null){
                        MinHouseMates = map.get("MinHouseMates").toString();
                        mMinHouseMates.setText(MinHouseMates);
                    }
                    if(map.get("MaxHouseMates")!=null){
                        MaxHouseMates = map.get("MaxHouseMates").toString();
                        mMaxHouseMates.setText(MaxHouseMates);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveUserInformation() {

        MinRent = mMinRent.getText().toString();
        MaxRent = mMaxRent.getText().toString();
        MinSize = mMinSize.getText().toString();
        MaxSize = mMaxSize.getText().toString();
        MinHouseMates = mMinHouseMates.getText().toString();
        MaxHouseMates = mMaxHouseMates.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("MinRent", MinRent);
        userInfo.put("MaxRent", MaxRent);
        userInfo.put("MinSize", MinSize);
        userInfo.put("MaxSize", MaxSize);
        userInfo.put("MinHouseMates", MinHouseMates);
        userInfo.put("MaxHouseMates", MaxHouseMates);

        mStudentAccountDatabase.updateChildren(userInfo);
    }

    //back button
    public void goToMainNavigationStudent(View view) {
        Intent intent = new Intent(ChangePreferences.this, MainNavigationStudent.class);
        startActivity(intent);
        return;
    }

    //back button
    public void goToMainNavigationStudentWithSave(View view) {
        /*if (NameStudent != null && Day != null && Month != null && Year != null
                && School != null && Study != null && Hobby1 != null && Hobby2 != null
                && Hobby3 != null && AboutMe != null) {
            saveUserInformation();
            Intent intent = new Intent(ChangeInformation.this, MainNavigationStudent.class);
            startActivity(intent);
            return;
        }*/
        saveUserInformation();
        Intent intent = new Intent(ChangePreferences.this, MainNavigationStudent.class);
        startActivity(intent);
        return;

    }
}
