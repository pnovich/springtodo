package com.example.springtodo;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "/**", methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/todos/find/{id}")
    public Todo getTodoById(@PathVariable("id") Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping("todos/create")
    public Todo createTodo(@RequestBody Todo todo) {
        valitadeTodo(todo);
        return todoRepository.save(todo);
    }

    @PutMapping("todos/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        valitadeTodo(todo);
        return todoRepository.save(todo);
    }

    @DeleteMapping("todos/delete/{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
    }

    @PostConstruct
    private void initDatabase() {
        List<Todo> todoList = Arrays.asList(
                new Todo(null, "todo1", Status.Active),
                new Todo(null, "todo2", Status.Active),
                new Todo(null, "todo3", Status.Active)
        );
        todoRepository.saveAll(todoList);
        logger.info("database initialized with default values");
    }

    private void valitadeTodo(Todo todo) {
        if (todo.getDescription() == null) {
            todo.setDescription("");
            logger.info("setting empty string as default description in todo");
        }
        if (todo.getStatus() == null) {
            todo.setStatus(Status.Active);
            logger.info("setting active as default status in todo");
        }
    }

}
