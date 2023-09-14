package com.example.springtodo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos/find/{id}")
    public Todo getTodoById(@PathVariable ("id") Long id) {
        return todoRepository.findById(id).get();
    }

    @PostMapping("todos/create")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("todos/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @DeleteMapping("todos/delete/{id}")
    public void deleteTodo(@PathVariable ("id") Long id) {
        todoRepository.deleteById(id);
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
