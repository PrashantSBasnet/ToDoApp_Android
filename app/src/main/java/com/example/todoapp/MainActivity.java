package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.todoapp.data.AppDatabase;
import com.example.todoapp.data.Task;
import com.example.todoapp.data.TodoDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = AppDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getDatabase(this);
        TodoDao dao = db.todoDao();
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {


            @Override
            public void run() {
                List<Task> tasks = dao.getAllTasks();
                Log.d(TAG, ""+tasks.size());
            }
        });

    }
}