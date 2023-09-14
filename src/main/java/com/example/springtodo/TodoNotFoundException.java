package com.example.springtodo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoNotFoundException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(TodoNotFoundException.class);

    TodoNotFoundException(Long id) {
        super("todo with id " + id + " not found");
        logger.error("not found todo with id " + id);
    }
}
