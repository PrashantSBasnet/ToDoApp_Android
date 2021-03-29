package com.example.todoapp.UI;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;
import java.util.List;


/**
 * ViewModel provides data to the UI and survive configuration changes
 * ViewModel acts as a communication center between the Repository and the UI
 * Android ViewModel contains application reference
 */

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    LiveData<List<Task>> taskList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        taskList = repository.getAllTasks();
    }


    public LiveData<List<Task>> getAllTasks(){
        return taskList;
    }

}
