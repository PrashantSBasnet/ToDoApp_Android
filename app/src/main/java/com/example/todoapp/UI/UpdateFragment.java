package com.example.todoapp.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.todoapp.R;
import com.example.todoapp.data.Task;


public class UpdateFragment extends Fragment {

    MainViewModel mTodoViewModel;
    private Task task;  //only in this class protected --package

    EditText edit_title,edit_desc;



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

        //to get the list clicked
        task=mTodoViewModel.getTask();


        //the values on the list that are clicked
        String update_description=  task.getDescription();
        String update_title= task.getTitle();


        edit_title = (EditText)view.findViewById(R.id.update_title);
        edit_desc = (EditText)view.findViewById(R.id.update_desc);


        //sets the data on the form
        edit_title.setText(update_title);
        edit_desc.setText(update_description);


        return  view;
    }
}