package com.springprojects.to_do.controller;

import com.springprojects.to_do.model.Todo;
import com.springprojects.to_do.service.TodoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController extends BaseController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo) {
        Todo newTodo = todoService.addTodo(todo, getLoggedInUsername());
        return new ResponseEntity<>(newTodo, CREATED);
    }

    @PutMapping
    public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(todo, getLoggedInUsername());
        return new ResponseEntity<>(updatedTodo, OK);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> listAllTodos() {
        List<Todo> todos = todoService.findByUsername(getLoggedInUsername());
        return new ResponseEntity<>(todos, OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Todo> findById(@PathVariable int id) {
        Optional<Todo> todo = todoService.findById(id, getLoggedInUsername());

        return todo.map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        todoService.deleteById(id, getLoggedInUsername());
        return new ResponseEntity<>(OK);
    }
}