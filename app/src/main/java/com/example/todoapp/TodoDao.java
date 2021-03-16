package com.example.todoapp;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface TodoDao {

    @Query("Select * from todos order by priority")
    List<Task> getAllTasks();

    @Delete
    void delete(Task task);

    void update(Task task);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Task task);

    @Query("delete from todos")
    void deleteAll();





}
