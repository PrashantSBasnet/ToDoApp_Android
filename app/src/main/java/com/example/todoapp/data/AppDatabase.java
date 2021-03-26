package com.example.todoapp.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)

// TODO (01) Create an abstract class that extends RoomDatabase.
public abstract class AppDatabase extends RoomDatabase {


    // TODO (04) Declare a @Volatile sINSTANCE variable.
public  static AppDatabase INSTANCE;

    // TODO (02) Declare an abstract value of type TaskDao.
public abstract TodoDao todoDao();


public static final int NUMBER_OF_THREADS=4;


public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);



   // TODO (06) Inside the synchronized block:
    public  static AppDatabase getDatabase(Context context){
        if (INSTANCE ==null){
            synchronized (AppDatabase.class){
                if (INSTANCE==null){

                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                            "todo.db").build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback CALLBACK = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                //run method is in different thread
                @Override
                public void run() {
                    TodoDao dao = INSTANCE.todoDao();
                    dao.deleteAll();
                    Task task = new Task("title", "description", new Date(), new Date(), 1);
                    dao.insert(task);
                    task = new Task("title", "description", new Date(), new Date(), 2);
                    dao.insert(task);

                }
            });


        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
