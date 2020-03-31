package com.example.roomdemo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cat_table")
class Cat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public String name;
    public Double weight;
}
