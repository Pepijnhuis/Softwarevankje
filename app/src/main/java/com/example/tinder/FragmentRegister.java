package com.example.tinder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentRegister extends Fragment {
    private FragmentblankListner;
    private EditText mName;
    private Button mRegister;

    public interface FragmentblankListner{
        void onInputBlankSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container,false);

        mName = v.findViewById(R.id.name);
        mRegister = v.findViewById(R.id.register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = mName.getText();
                listener.onInputBlankSent;
            }
        });

        return v;


    }

    @Override
    public void onAttatch (Context context){
        super.onAttach(context);
        if (context instanceof FragmentblankListner){
            listener = (FragmentblankListner) context;
        }
        else {
            throw new RuntimeException()context.toString() + "must inplement Fragmentblanklistener"
        }
    }
}
