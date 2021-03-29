package com.example.todoapp.UI;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class TodoFragment extends Fragment implements  RecyclerViewClickInterface {

    private FloatingActionButton fab;
    public TaskAdapter adapter;
    public MainViewModel mTodoViewModel;

    public static TodoFragment newInstance() {
        return new TodoFragment();
    }

    public TodoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view;

        view = inflater.inflate(R.layout.fragment_todo, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        this.adapter = new TaskAdapter(this,this);



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fab = view.findViewById(R.id.floatingActionButton);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // deprecated, mTodoViewModel = of(this).get(TodoViewModel.class);
         mTodoViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        // ToDo: Using the ViewModel

        // Add an observer on the LiveData returned by getTodos.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.

        mTodoViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {

            @Override
            public void onChanged(@Nullable final List<Task> todos) {
                // Update the cached copy of the todos in the adapter.
                adapter.setData(todos);
            }

        });



        //switching between fragments
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Add_Data_Fragments.newInstance())
                        .commitNow();
            }
        });

    }

    @Override
    public int onItemClick(int position) {
        Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();

        List<Task>listofTasks= adapter.getTaskList();

        Task task= listofTasks.get(position);  //value received


        mTodoViewModel.setTask(task);
        //mTodoViewModel.getTask();


        //Transaction

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, UpdateFragment.newInstance())
                .commitNow();


        return position;
    }

    @Override
    public void onLongItemClick(int position) {

    }
}