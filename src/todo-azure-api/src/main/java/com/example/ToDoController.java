package com.example;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.openapi.api.ListsApi;
import com.example.openapi.api.ListsApiDelegate;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

@Component
public class ToDoController implements ListsApiDelegate {

    private static Map<TodoList, Set<TodoItem>> todoListItems = new HashMap<>();

    private static AtomicInteger nextItemId = new AtomicInteger(1);

    @Override
    public ResponseEntity<List<TodoList>> getLists(BigDecimal top, BigDecimal skip) {
        return new ResponseEntity<List<TodoList>>(List.copyOf(todoListItems.keySet()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoList> getListById(String listId) {
        var todoList = todoListItems.keySet().stream().filter(l -> l.getId().equals(listId)).findFirst();

        if (todoList.isPresent()) {
            return new ResponseEntity<TodoList>(todoList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TodoList>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<TodoList> createList(TodoList todoList) {
        todoList.setId(String.valueOf(todoListItems.size()));
        todoListItems.put(todoList, new HashSet<TodoItem>());
        return new ResponseEntity<>(todoList, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteListById(String listId) {
        var todoList = todoListItems.keySet().stream().filter(l -> l.getId().equals(listId)).findFirst();
        if (todoList.isPresent() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todoListItems.remove(todoList.get());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<TodoList> updateListById(String listId, TodoList updatedTodoList) {
        var todoListOpt = todoListItems.keySet().stream().filter(l -> l.getId().equals(listId)).findFirst();
        if (todoListOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var todoList = todoListOpt.get();
        todoList.setName(updatedTodoList.getName());
        todoList.setDescription(updatedTodoList.getDescription());
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    /**
     * POST /lists/{listId}/items : Creates a new Todo item within a list
     *
     * @param listId   The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 201)
     *         or Todo list not found (status code 404)
     * @see ListsApi#createItem
     */
    @Override
    public ResponseEntity<TodoItem> createItem(String listId, TodoItem todoItem) {
        var todoListOpt = todoListItems.keySet().stream().filter(l -> l.getId().equals(listId)).findFirst();
        if (todoListOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        todoItem.setId(String.valueOf(nextItemId.addAndGet(1)));

        if (todoItem.getState() == null) {
            todoItem.setState(TodoState.TODO);
        }

        if (todoItem.getDueDate() == null) {
            // Set due date to Two Days from now
            todoItem.setDueDate(OffsetDateTime.now().plusDays(2));
        }

        todoListItems.get(todoListOpt.get()).add(todoItem);
        return new ResponseEntity<>(todoItem, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TodoItem> getItemById(String listId, String itemId) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.getItemById(listId, itemId);
    }

    @Override
    public ResponseEntity<Void> deleteItemById(String listId, String itemId) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.deleteItemById(listId, itemId);
    }

    @Override
    public ResponseEntity<List<TodoItem>> getItemsByListId(String listId, BigDecimal top, BigDecimal skip) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.getItemsByListId(listId, top, skip);
    }

    @Override
    public ResponseEntity<List<TodoItem>> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top,
            BigDecimal skip) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.getItemsByListIdAndState(listId, state, top, skip);
    }

    @Override
    public ResponseEntity<TodoItem> updateItemById(String listId, String itemId, TodoItem todoItem) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.updateItemById(listId, itemId, todoItem);
    }

    @Override
    public ResponseEntity<Void> updateItemsStateByListId(String listId, TodoState state, List<String> requestBody) {
        // TODO Auto-generated method stub
        return ListsApiDelegate.super.updateItemsStateByListId(listId, state, requestBody);
    }

}
