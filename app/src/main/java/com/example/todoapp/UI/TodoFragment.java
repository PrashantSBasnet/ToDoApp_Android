package com.example.todoapp.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        this.adapter = new TaskAdapter(this,this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Task> taskList= adapter.getTaskList();
                mTodoViewModel.deleteTask(taskList.get(position));

            }
        }).attachToRecyclerView(recyclerView);
        //------------------------





        //for setting floating button
        fab = view.findViewById(R.id.floatingActionButton);

        return view;
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //getActivity() instead of this because ToDoFragment not MainActivity
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





        //--------switching between fragments
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Add_Data_Fragments.newInstance())
                        .commitNow();
            }
        });
        //---------------------------------

    }


    //when a to_do item is clicked
    @Override
    public int onItemClick(int position) {
        Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();

        List<Task>listofTasks= adapter.getTaskList();

        Task task= listofTasks.get(position);  //value received

        mTodoViewModel.setTask(task);
        //mTodoViewModel.getTask();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, UpdateFragment.newInstance())
                .commitNow();

        return position;
    }


}