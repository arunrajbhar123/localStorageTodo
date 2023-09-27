package com.example.todoappx;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<TodoList> todoLists;

    public RecyclerViewAdapter(Context context, ArrayList<TodoList> todoLists) {
        this.context = context;
        this.todoLists = todoLists;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemcardview, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        final TodoList currentItem = todoLists.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.isChecked.setChecked(currentItem.isChecked());

        boolean isChecked = currentItem.isChecked();

        if (isChecked) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        } else {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | (~Paint.STRIKE_THRU_TEXT_FLAG));
        }


    }

    @Override
    public int getItemCount() {
        return todoLists.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox isChecked;

        ImageButton deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.textView);
            isChecked = itemView.findViewById(R.id.checkBox);
            deleteBtn = itemView.findViewById(R.id.imageButton);


//            here handling the onChange for CheckBox

            isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        todoLists.get(position).setChecked(b);
                        if (b) {
                            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        } else {
                            title.setPaintFlags(title.getPaintFlags() | (~Paint.STRIKE_THRU_TEXT_FLAG));
                        }

                    }

                }
            });


        }
    }
}
