package com.example.todoapp.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;

//fist ui
public class Add_Data_Fragments extends Fragment {

    public Add_Data_Fragments()
    {

    }

    public static Fragment newInstance() {
        return new Add_Data_Fragments();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add__data__fragments, container, false);


        return view;
    }
}