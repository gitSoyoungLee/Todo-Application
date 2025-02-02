package com.example.todo.dto;

import com.example.todo.entity.Todo;

public class TodoSimpleResponseDto {
    private int id;
    private String title;

    public TodoSimpleResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
    }

    public int getId() { return this.id; }
    public String getTitle() { return this.title; }
}
