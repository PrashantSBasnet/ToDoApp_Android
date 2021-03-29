package com.example.todoapp.UI;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapp.R;
import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Date;

//fist ui
public class Add_Data_Fragments extends Fragment {


    //variables for objects and views
    private EditText titleEditText;
    private EditText descEditText;
    private Button submitButton;
    private MainViewModel todoViewModel;
    private Repository repository;

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

        titleEditText = view.findViewById(R.id.title_et);
        descEditText = view.findViewById(R.id.desc_dt);
        submitButton = view.findViewById(R.id.submit_btn);

        repository = Repository.getRepository(getActivity().getApplication());

        // submitButton = view.findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {



            //to save data
            @Override
            public void onClick(View v) {

                String title = titleEditText.toString();
                String desc = descEditText.toString();



                Task task = new Task(title, desc,  new Date(), new Date(), 1);
                repository.addTask(task);

                //switches to the main fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();
            }
        });



        return view;
    }


}