package com.example;

import java.math.BigDecimal;
import java.util.List;

import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

public class CosmosDBToDoRepository implements ToDoRepository {

    @Override
    public TodoItem createItem(String listId, TodoItem todoItem) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TodoList createList(TodoList todoList) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteItemById(String listId, String itemId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteListById(String listId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TodoItem getItemById(String listId, String itemId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TodoItem> getItemsByListId(String listId, BigDecimal top, BigDecimal skip) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TodoItem> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top, BigDecimal skip) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TodoList getListById(String listId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TodoList> getLists(BigDecimal top, BigDecimal skip) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TodoItem updateItemById(String listId, String itemId, TodoItem todoItem) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateItemsStateByListId(String listId, TodoState state, List<String> requestBody) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TodoList updateListById(String listId, TodoList todoList) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
