package com.example.todoappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoList> todoItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView todoRecyclerView = findViewById(R.id.todoRecyclerView);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);


        for (int i = 1; i < 20; i++) {
            boolean condition = (i % 2 == 0);
            todoItemsList.add(new TodoList("First Task " + i, condition));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, todoItemsList);
        todoRecyclerView.setAdapter(adapter);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setItemAnimator(new DefaultItemAnimator());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupDialog(adapter, todoRecyclerView);
            }
        });

    }

    private void showPopupDialog(RecyclerView.Adapter adapter, RecyclerView todoRecyclerView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_layout, null);
        TextInputLayout textField = dialogView.findViewById(R.id.textField);
        builder.setView(dialogView);

        builder.setTitle("Enter Task.");
        builder.setPositiveButton("OK", null);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = textField.getEditText().getText().toString();
                if (!TextUtils.isEmpty(title)) {
                    todoItemsList.add(new TodoList(title, false));
                    adapter.notifyItemInserted(todoItemsList.size() - 1);
                    todoRecyclerView.smoothScrollToPosition(todoItemsList.size() - 1);
                    Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}