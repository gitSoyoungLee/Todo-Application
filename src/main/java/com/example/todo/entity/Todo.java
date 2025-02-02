package com.example.todo.entity;

import com.example.todo.dto.TodoRequestDto;

public class Todo {
    private int id;
    private String title;
    private String content;

    public Todo(int id, TodoRequestDto todoRequestDto) {
        this.id = id;
        this.title = todoRequestDto.getTitle();
        this.content= todoRequestDto.getContent();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void updateTodo(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.getTitle();
        this.content= todoRequestDto.getContent();
    }
}
