package com.example.tinder;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.storage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class CA4StudentFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA4StudentFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca4_student_fragment, container, false);
        return view;
    }

    private void saveUserImage() {

        StorageReference filepath = FirebaseStorage.getInstance().getReference().child("ProfileImage").child(Image1);
        Bitmap bitmap = null;

        bitmap = MediaStore.Images.Media.getBitmap(getA

    }

}