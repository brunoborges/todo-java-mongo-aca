package com.example;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.openapi.api.ListsApiDelegate;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

@Component
public class TodoListDelegateImpl implements ListsApiDelegate {

    @Autowired
    private TodoListRepository toDoRepository;

    @Override
    public ResponseEntity<List<TodoList>> getLists(BigDecimal top, BigDecimal skip) {
        var lists = toDoRepository.getLists(top, skip);
        return new ResponseEntity<List<TodoList>>(lists, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoList> getListById(String listId) {
        var todoList = toDoRepository.getListById(listId);
        var status = todoList != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<TodoList>(todoList, status);
    }

    @Override
    public ResponseEntity<TodoList> createList(TodoList todoList) {
        toDoRepository.createList(todoList);
        return new ResponseEntity<>(todoList, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteListById(String listId) {
        var deleted = toDoRepository.deleteListById(listId);
        return new ResponseEntity<Void>(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<TodoList> updateListById(String listId, TodoList todoList) {
        var updatedTodoList = toDoRepository.updateListById(listId, todoList);
        return new ResponseEntity<>(updatedTodoList, updatedTodoList != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<TodoItem> createItem(String listId, TodoItem todoItem) {
        var createdItem = toDoRepository.createItem(listId, todoItem);
        var httpStatus = createdItem != null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(createdItem, httpStatus);
    }

    @Override
    public ResponseEntity<Void> deleteItemById(String listId, String itemId) {
        var deleted = toDoRepository.deleteItemById(listId, itemId);
        return new ResponseEntity<>(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<TodoItem> getItemById(String listId, String itemId) {
        var item = toDoRepository.getItemById(listId, itemId);
        return new ResponseEntity<>(item, item == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoItem>> getItemsByListId(String listId, BigDecimal top, BigDecimal skip) {
        var items = toDoRepository.getItemsByListId(listId, top, skip);
        return new ResponseEntity<List<TodoItem>>(items, items.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoItem>> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top,
            BigDecimal skip) {
        var items = toDoRepository.getItemsByListIdAndState(listId, state, top, skip);
        return new ResponseEntity<>(items, items.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoItem> updateItemById(String listId, String itemId, TodoItem todoItem) {
        var updatedTodoItem = toDoRepository.updateItemById(listId, itemId, todoItem);
        return new ResponseEntity<>(updatedTodoItem, updatedTodoItem != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Void> updateItemsStateByListId(String listId, TodoState state, List<String> requestBody) {
        var updated = toDoRepository.updateItemsStateByListId(listId, state, requestBody);
        return new ResponseEntity<>(updated ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
    }

}
