package com.example.tinder.CreateAccount;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tinder.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CA4HouseFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA4HouseFragment";

    private Button mButtonNext;
    private ImageView mImageroom;
    private Uri resultUri;
    private FirebaseAuth mAuth;
    private String Image1, userId; //downloadUrl;

    private DatabaseReference mStudentAccountDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;


    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca4_house_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //next button
        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA4House);


        mImageroom = (ImageView) view.findViewById(R.id.imageRoom);

        mImageroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        //next button
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserImage();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountHouse, new CA5HouseFragment());
                fr.commit();
            }
        });


        return view;
    }


    private void saveUserImage() {
        if (resultUri != null) {

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

            ref.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) ;
                    Uri downloadUrl = urlTask.getResult();
                    Log.d("Debug", downloadUrl.toString());

                    userId = mAuth.getCurrentUser().getUid();
                    Log.d("Debug", userId);
                    mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                    Map userInfo = new HashMap();
                    userInfo.put("ProfileImageUrl", downloadUrl.toString());
                    Log.d("Debug", String.valueOf(userInfo));
                    mStudentAccountDatabase.updateChildren(userInfo);

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            resultUri = data.getData();;
            mImageroom.setImageURI(resultUri);
        }
    }
}