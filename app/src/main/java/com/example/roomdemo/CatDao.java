package com.example.roomdemo;

import androidx.room.*;

import java.util.List;

@Dao
public interface CatDao {

    @Insert
    public void insert(Cat aCat);

    @Query("SELECT * FROM cat_table")
    List<Cat> getAll();


}
