package com.example.todoapp.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.data.Task;


public class UpdateFragment extends Fragment {

    MainViewModel mTodoViewModel;
    private Task task;  //only in this class protected --package

    public UpdateFragment() {

    }

    public static Fragment newInstance() {
        return  new UpdateFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_update, container, false);

        mTodoViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        task=mTodoViewModel.getTask();



        return  view;
    }
}