package com.springprojects.to_do.repository;

import com.springprojects.to_do.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByUsername(String username);
}
