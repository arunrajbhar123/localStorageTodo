package com.example.todoappx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoList> todoItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView todoRecyclerView = findViewById(R.id.todoRecyclerView);

        for (int i = 1; i < 20; i++) {
            boolean condition = (i % 2 == 0);
            todoItemsList.add(new TodoList("First Task", condition));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, todoItemsList);
        todoRecyclerView.setAdapter(adapter);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}