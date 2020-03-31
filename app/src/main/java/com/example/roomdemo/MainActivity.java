package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnimalsDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AnimalsDatabase.class,
                "animals_db").build();


        Button addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "add button clicked");
                EditText inputET = findViewById(R.id.inputET);
                String theName = inputET.getText().toString();
                Cat myCat = new Cat();
                myCat.name = theName;
                myCat.weight = 10.0;
                threadInsert(myCat);

            }
        });

        Button fetchAllBTN = findViewById(R.id.fetchAllBTN);
        fetchAllBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "fetch button clicked");
                threadGetAll();
            }
        });

        Button fetchSomeBTN = findViewById(R.id.fetchSomeBTN);
        fetchSomeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "fetch some button clicked");
                threadGetSome();
            }
        });

        Button updateAllBTN = findViewById(R.id.updateAllBTN);
        updateAllBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "update all button clicked");
                threadUpdateAll();
            }
        });

        Button deleteSomeBTN = findViewById(R.id.deleteSomeBTN);
        deleteSomeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "delete some button clicked");
                threadDeleteSome();
            }
        });

    }

    public void threadInsert(final Cat c){
        new Thread(
                new Runnable() {
            @Override
            public void run() {
                db.catDao().insert(c);
                List<Cat> catList = db.catDao().getAll();
                for (Cat cat : catList) {
                    Log.d("DB", "cat: " + cat.id
                            + " named " + cat.name
                            + " weighs " + cat.weight);
                }
            }
        }).start();
    }

    public void threadGetAll(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        List<Cat> catList = db.catDao().getAll();
                        String result = "";
                        for (Cat cat : catList) {
                            String piece =  "cat: " + cat.id
                                    + " named " + cat.name
                                    + " weighs " + cat.weight;
                            Log.d("DB", piece);
                            result = result + piece + "\n";
                        }
                        final String built = result;
                        final TextView resultTV = findViewById(R.id.resultTV);
                        resultTV.post(new Runnable() {
                            @Override
                            public void run() {
                                resultTV.setText(built);
                            }
                        });

                    }
                }).start();
    }


    public void threadGetSome(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        List<Cat> catList = db.catDao().getByName("Fluff");
                        String result = "";
                        for (Cat cat : catList) {
                            String piece =  "cat: " + cat.id
                                    + " named " + cat.name
                                    + " weighs " + cat.weight;
                            Log.d("DB", piece);
                            result = result + piece + "\n";
                        }
                        final String built = result;
                        final TextView resultTV = findViewById(R.id.resultTV);
                        resultTV.post(new Runnable() {
                            @Override
                            public void run() {
                                resultTV.setText(built);
                            }
                        });

                    }
                }).start();
    }

    public void threadUpdateAll(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        List<Cat> catList = db.catDao().getAll();
                        for (Cat cat : catList) {
                            // Got rid of the Log
                            cat.weight += cat.id;
                            db.catDao().update(cat);
                        }
                    }
                }).start();
    }

    public void threadDeleteSome(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        List<Cat> catList = db.catDao().getByName("Fluff");
                        for (Cat cat : catList) {
                            db.catDao().delete(cat);
                        }
                    }
                }).start();
    }
}
