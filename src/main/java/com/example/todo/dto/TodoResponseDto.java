package com.example.todo.dto;

import com.example.todo.entity.Todo;

public class TodoResponseDto {
    private int id;
    private String title;
    private String content;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
    }

    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getContent() { return this.content; }
}
