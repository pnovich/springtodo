package com.example.springtodo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "/**", methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class TodoController {
    @Autowired
    TodoRepository todoRepository;
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostConstruct
    private void initDatabase() {
        List<Todo> todoList = Arrays.asList(
                new Todo(null,"todo1", Status.Active),
                new Todo(null,"todo2", Status.Active),
                new Todo(null,"todo3", Status.Active)
                );
        todoRepository.saveAll(todoList);
    }

}
