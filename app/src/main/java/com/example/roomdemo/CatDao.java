package com.example.roomdemo;

import androidx.room.*;

import java.util.List;

@Dao
public interface CatDao {

    @Insert
    public void insert(Cat aCat);

    @Query("SELECT * FROM cat_table")
    List<Cat> getAll();

    @Query("SELECT * FROM cat_table WHERE name = :lookFor")
    List<Cat> getByName(String lookFor);

    @Update
    public void update(Cat c);

    @Delete
    public void delete(Cat c);
}
