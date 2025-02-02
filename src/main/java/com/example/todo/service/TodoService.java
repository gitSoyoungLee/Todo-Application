package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.dto.TodoSimpleResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);

    List<TodoSimpleResponseDto> getList();

    TodoResponseDto getTodoById(int id);

    TodoResponseDto updateTodo(int id, TodoRequestDto todoRequestDto);

    void deleteTodo(int id);
}
