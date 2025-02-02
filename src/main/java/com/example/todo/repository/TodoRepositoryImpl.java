package com.example.todo.repository;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private Map<Integer, Todo> data;
    private int count;

    public TodoRepositoryImpl() {
        data = new HashMap<>();
        count= 1;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void save(Todo todo) {
        data.put(todo.getId(), todo);
        count++;
    }

    @Override
    public void delete(int id) {
        data.remove(id);
    }

    @Override
    public Todo findById(int id) {
        return Optional.ofNullable(data.get(id))
                .orElseThrow(() -> new NoSuchElementException(id + ": 존재하지 않는 아이디입니다."));
    }

    @Override
    public Map<Integer, Todo> findAll() {
        return new HashMap<>(data);
    }


}
