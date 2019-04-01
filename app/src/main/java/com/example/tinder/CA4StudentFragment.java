package com.example.tinder;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CA4StudentFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA4StudentFragment";

    private Button mButtonNext, mSkip;
    private ImageView mProfileImage1;
    private Uri resultUri;
    private String Image1, userId; //downloadUrl;
    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;


    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca4_student_fragment, container, false);

        mSkip = (Button) view.findViewById(R.id.ButtonBackCA4Student);

        //skip button
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountStudent,new CA5StudentFragment());
                fr.commit();
            }
        });

        mAuth= FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mProfileImage1 = (ImageView) view.findViewById(R.id.imageStudent1);

        mProfileImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });


        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA4Student);

        //mButtonNext.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View v) {
        //Edittext to String
        //Email = mEmail.getText().toString();
        //Password = mPassword.getText().toString();
        //listener.onInputCA1StudentSent(Email,Password);

        //next button
        //if (Email != null && Password !=null){
        //FragmentTransaction fr = getFragmentManager().beginTransaction();
        //fr.replace(R.id.containerCreateAccountStudent,new CA5StudentFragment());
        //fr.commit();
        //}
        //}
        //});

        //next buttons
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserImage();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountStudent,new CA5StudentFragment());
                fr.commit();
            }
        });
        return view;
    }

    private void saveUserImage() {
        //mStudentAccountDatabase.updateChildren();

        if(resultUri != null) {

            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("Users").child("Student").child(userId);
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 15, baos);
            byte[] data = baos.toByteArray();
            final UploadTask uploadTask = filepath.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(getActivity(), "Fialure image", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //taskSnapshot.getDownloadUrl is obsolete
                    //String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    String downloadUrl = FirebaseStorage.getInstance().getReference().toString(); //gives wrong url

                    //StorageReference downloadUrl = FirebaseStorage.getInstance().getReference(); gives wrong url
                    //return dateRef.toString();

                    //String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(); //gives wrong url

                    Map userInfo = new HashMap();
                    userInfo.put("ProfileImageUrl", downloadUrl);
                    Log.d("Debug", String.valueOf(userInfo));
                    mStudentAccountDatabase.updateChildren(userInfo);

                    getActivity().finish();
                    return;
                }
            });
        }

        else {getActivity().finish();}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            final Uri imageUri  = data.getData();
            resultUri = imageUri;
            mProfileImage1.setImageURI(resultUri);
        }
    }
}
