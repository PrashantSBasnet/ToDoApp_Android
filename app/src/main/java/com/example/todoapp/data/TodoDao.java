package com.example.todoapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.data.Task;

import java.util.List;


/** creating interface to follow the design pattern
    crud operation
    dao using query method
 **/

@Dao
public interface TodoDao {

    @Query("Select * from todos order by priority")
    List<Task> getAllTasks();

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Task task);

    @Query("delete from todos")
    void deleteAll();
}
