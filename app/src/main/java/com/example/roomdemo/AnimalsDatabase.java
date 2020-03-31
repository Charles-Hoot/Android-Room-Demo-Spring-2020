package com.example.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cat.class}, version = 1)
public abstract class AnimalsDatabase extends RoomDatabase {
    public abstract CatDao catDao();
}
