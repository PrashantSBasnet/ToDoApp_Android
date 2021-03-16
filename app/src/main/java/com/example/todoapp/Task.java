package com.example.todoapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "todos")


public class Task {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String title;
    private String description;
    @ColumnInfo(name = "created_date")
    private Date createdDate;
    @ColumnInfo(name="updated_date")
    private Date updatedDate;
    private int priority;




    //constructor -- autogenerated by control+enter
    //room data base is for writing therefore this constructor is ignored
    @Ignore
    public Task(Long id, String title, String description, Date createdDate, Date updatedDate, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.priority = priority;
    }


    //constructor with no id
    //used for reading
    public Task(String title, String description, Date createdDate, Date updatedDate, int priority) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.priority = priority;
    }



    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public int getPriority() {
        return priority;
    }


}
