package com.example.todoappx;

public class TodoList {

    private String title;
    private boolean isChecked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public TodoList(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
