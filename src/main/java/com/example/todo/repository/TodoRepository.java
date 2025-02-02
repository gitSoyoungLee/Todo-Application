package com.example.todo.repository;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.entity.Todo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository {
    public int getCount();

    void save(Todo todo);

    void delete(int id);

    Todo findById(int id);

    Map<Integer, Todo> findAll();

}
