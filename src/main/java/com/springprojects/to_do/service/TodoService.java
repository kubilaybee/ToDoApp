package com.springprojects.to_do.service;

import com.springprojects.to_do.exception.types.DataNotFoundException;
import com.springprojects.to_do.exception.types.PermissionException;
import com.springprojects.to_do.model.Todo;
import com.springprojects.to_do.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo, String loggedInUsername) throws Exception {
        if (!todo.getUsername().equalsIgnoreCase(loggedInUsername)) {
            String errorMessage = "Permission Denied: User " + loggedInUsername + " cannot add a todo for user " + todo.getUsername() + ".";
            throw new PermissionException(loggedInUsername, "TodoService", loggedInUsername);
        }
        return todoRepository.saveAndFlush(todo);
    }

    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    public Optional<Todo> findById(int id, String loggedInUsername) throws Exception {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            throw new DataNotFoundException(loggedInUsername, "TodoService", id + "");
        }
        if (!todo.get().getUsername().equalsIgnoreCase(loggedInUsername)) {
            throw new PermissionException(loggedInUsername, "TodoService", loggedInUsername);
        }
        return todo;
    }

    public Todo updateTodo(Todo updateTodo, String loggedInUsername) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(updateTodo.getId());

        if (optionalTodo.isEmpty()) {
            throw new DataNotFoundException(updateTodo.getId() + "", "TodoService", updateTodo.getId() + "");
        }

        Todo existingTodo = optionalTodo.get();
        if (!existingTodo.getUsername().equalsIgnoreCase(loggedInUsername)) {
            throw new PermissionException(loggedInUsername, "TodoService", loggedInUsername);
        }

        existingTodo.setDescription(updateTodo.getDescription());
        existingTodo.setTargetDate(updateTodo.getTargetDate());
        existingTodo.setDone(updateTodo.isDone());

        return todoRepository.saveAndFlush(existingTodo);
    }

    public void deleteById(int id, String loggedInUsername) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isEmpty()) {
            throw new DataNotFoundException(id + "", "TodoService", id + "");
        }

        Todo todoToDelete = optionalTodo.get();
        if (!todoToDelete.getUsername().equalsIgnoreCase(loggedInUsername)) {
            throw new PermissionException(loggedInUsername, "TodoService", loggedInUsername);
        }

        todoRepository.deleteById(id);
    }
}