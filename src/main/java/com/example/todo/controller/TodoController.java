package com.example.todo.controller;

import com.example.todo.dto.ResponseMessage;
import com.example.todo.dto.TodoRequestDto;
import com.example.todo.dto.TodoResponseDto;
import com.example.todo.dto.TodoSimpleResponseDto;
import com.example.todo.service.TodoService;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // TODO 항목 작성 및 등록
    @PostMapping("/create")
    public ResponseEntity<Object> saveTodo(@RequestBody TodoRequestDto todoRequestDto) {
        try {
            TodoResponseDto todoResponseDto = todoService.createTodo(todoRequestDto);
            return ResponseEntity.ok(todoResponseDto);
        } catch (IllegalArgumentException
                e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    // TODO 항목(제목) 리스트 불러오기
    @GetMapping("/load/list")
    public ResponseEntity<List<TodoSimpleResponseDto>> loadList() {
        return ResponseEntity.ok(todoService.getList());
    }

    // TODO 항목 제목 및 상세 내용 조회
    @GetMapping("/load/{id}")
    public ResponseEntity<Object> loadTodo(@PathVariable(required = true) int id){
        try {
            TodoResponseDto todoResponseDto = todoService.getTodoById(id);
            return ResponseEntity.ok(todoResponseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    //(Optional) TODO 항목 업데이트
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable(required = true) int id,
                                             @RequestBody TodoRequestDto todoRequestDto) {
        try {
            TodoResponseDto todoResponseDto = todoService.updateTodo(id, todoRequestDto);
            return ResponseEntity.ok(todoResponseDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    //(Optional) TODO 항목 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable(required = true) int id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}
