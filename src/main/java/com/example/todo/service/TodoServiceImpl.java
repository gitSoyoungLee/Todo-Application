package com.example.todo.service;

import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.dto.TodoSimpleResponseDto;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.TodoRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        // title과 content가 null이거나 빈 문자열인지 체크
        if (todoRequestDto.getTitle() == null || todoRequestDto.getTitle().trim().isEmpty() ||
                todoRequestDto.getContent() == null || todoRequestDto.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("형식에 맞춰 작성해주세요.");
        }
        Todo todo = new Todo(todoRepository.getCount(), todoRequestDto);
        todoRepository.save(todo);
        return new TodoResponseDto(todo);
    }

    @Override
    public List<TodoSimpleResponseDto> getList() {
        Map<Integer, Todo> map = todoRepository.findAll();
        List<TodoSimpleResponseDto> list = new ArrayList<>();
        map.values().stream()
                .sorted(Comparator.comparing(todo -> todo.getId()))
                .forEach(todo -> list.add(new TodoSimpleResponseDto(todo)));
        return list;
    }

    @Override
    public TodoResponseDto getTodoById(int id) {
        try {
            return new TodoResponseDto(todoRepository.findById(id));
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    @Override
    public TodoResponseDto updateTodo(int id, TodoRequestDto todoRequestDto) {
        try {
            // title과 content가 null이거나 빈 문자열인지 체크
            if (todoRequestDto.getTitle() == null || todoRequestDto.getTitle().trim().isEmpty() ||
                    todoRequestDto.getContent() == null || todoRequestDto.getContent().trim().isEmpty()) {
                throw new IllegalArgumentException("형식에 맞춰 작성해주세요.");
            }
            Todo todo = todoRepository.findById(id);
            todo.updateTodo(todoRequestDto);
            return new TodoResponseDto(todo);
        } catch (NoSuchElementException e) {
            throw e;
        }

    }

    @Override
    public void deleteTodo(int id) {
        todoRepository.delete(id);
    }
}
