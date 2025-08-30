package com.springprojects.to_do.service;

import com.springprojects.to_do.model.Todo;
import com.springprojects.to_do.repository.TodoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo, String loggedInUsername) {
        if (!todo.getUsername().equalsIgnoreCase(loggedInUsername)) {
            String errorMessage = "Permission Denied: User " + loggedInUsername + " cannot add a todo for user " + todo.getUsername() + ".";
            throw new PermissionDeniedDataAccessException(errorMessage, null);
        }
        return todoRepository.saveAndFlush(todo);
    }

    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    public Optional<Todo> findById(int id, String loggedInUsername) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            throw new IllegalArgumentException("Todo with ID " + id + " not found.");
        }
        if (!todo.get().getUsername().equalsIgnoreCase(loggedInUsername)) {
            String errorMessage = "Permission Denied !";
            throw new PermissionDeniedDataAccessException(errorMessage, null);
        }
        return todo;
    }

    public Todo updateTodo(Todo updateTodo, String loggedInUsername) {
        Optional<Todo> optionalTodo = todoRepository.findById(updateTodo.getId());

        if (optionalTodo.isEmpty()) {
            throw new IllegalArgumentException("Todo with ID " + updateTodo.getId() + " not found.");
        }

        Todo existingTodo = optionalTodo.get();
        if (!existingTodo.getUsername().equalsIgnoreCase(loggedInUsername)) {
            String errorMessage = "Permission Denied: User " + loggedInUsername + " is not authorized to update this todo.";
            throw new PermissionDeniedDataAccessException(errorMessage, null);
        }

        existingTodo.setDescription(updateTodo.getDescription());
        existingTodo.setTargetDate(updateTodo.getTargetDate());
        existingTodo.setDone(updateTodo.isDone());

        return todoRepository.saveAndFlush(existingTodo);
    }

    public void deleteById(int id, String loggedInUsername) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isEmpty()) {
            throw new IllegalArgumentException("Todo with ID " + id + " not found.");
        }

        Todo todoToDelete = optionalTodo.get();
        if (!todoToDelete.getUsername().equalsIgnoreCase(loggedInUsername)) {
            String errorMessage = "Permission Denied: User " + loggedInUsername + " is not authorized to delete this todo.";
            throw new PermissionDeniedDataAccessException(errorMessage, null);
        }

        todoRepository.deleteById(id);
    }
}