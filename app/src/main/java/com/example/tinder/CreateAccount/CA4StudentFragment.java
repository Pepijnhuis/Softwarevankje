package com.example.tinder.CreateAccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tinder.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
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
import java.util.UUID;


public class CA4StudentFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA4StudentFragment";

    private Button mButtonNext;
    private ImageView mProfileImage1;
    private Uri resultUri;
    private String Image1, userId; //downloadUrl;

    private FirebaseAuth mAuth;
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

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        View view = inflater.inflate(R.layout.ca4_student_fragment, container, false);

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

        //next buttonshape
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
        if (resultUri != null){


            StorageReference ref =  storageReference.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
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







        //mStudentAccountDatabase.updateChildren();

       /* if(resultUri != null) {
            
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

                    //String downloadUrl = FirebaseStorage.getInstance().getReference().getDownloadUrl().toString();
                    Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();


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

        */



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            resultUri = data.getData();;
            mProfileImage1.setImageURI(resultUri);
        }
    }
}
