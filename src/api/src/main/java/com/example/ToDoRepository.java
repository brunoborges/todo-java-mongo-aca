package com.example;

import java.math.BigDecimal;
import java.util.List;

import com.example.openapi.api.ListsApi;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

public interface ToDoRepository {

    /**
     * Creates a new Todo item within a list
     *
     * @param listId   The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 201)
     *         or Todo list not found (status code 404)
     * @see ListsApi#createItem
     */
    public TodoItem createItem(String listId, TodoItem todoItem);

    /**
     * Creates a new Todo list
     *
     * @param todoList The Todo List
     * @return A Todo list result
     * @see ListsApi#createList
     */
    public TodoList createList(TodoList todoList);

    /**
     * Deletes a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return Todo item deleted successfully (status code 204)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#deleteItemById
     */
    public boolean deleteItemById(String listId, String itemId);

    /**
     * Deletes a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return Todo list deleted successfully (status code 204)
     *         or Todo list not found (status code 404)
     * @see ListsApi#deleteListById
     */
    public boolean deleteListById(String listId);

    /**
     * Gets a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return A Todo item result (status code 200)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#getItemById
     */
    public TodoItem getItemById(String listId, String itemId);

    /**
     * Gets Todo items within the specified list
     *
     * @param listId The Todo list unique identifier (required)
     * @param top    The max number of items to returns in a result (optional)
     * @param skip   The number of items to skip within the results (optional)
     * @return An array of Todo items (status code 200)
     *         or Todo list not found (status code 404)
     * @see ListsApi#getItemsByListId
     */
    public List<TodoItem> getItemsByListId(String listId, BigDecimal top, BigDecimal skip);

    /**
     * Gets a list of Todo items of a specific state
     *
     * @param listId The Todo list unique identifier (required)
     * @param state  The Todo item state (required)
     * @param top    The max number of items to returns in a result (optional)
     * @param skip   The number of items to skip within the results (optional)
     * @return An array of Todo items (status code 200)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#getItemsByListIdAndState
     */
    public List<TodoItem> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top, BigDecimal skip);

    /**
     * Gets a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return A Todo list result (status code 200)
     *         or Todo list not found (status code 404)
     * @see ListsApi#getListById
     */
    public TodoList getListById(String listId);

    /**
     * Gets an array of Todo lists
     *
     * @param top  The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo lists (status code 200)
     * @see ListsApi#getLists
     */
    public List<TodoList> getLists(BigDecimal top,
            BigDecimal skip);

    /**
     * Updates a Todo item by unique identifier
     *
     * @param listId   The Todo list unique identifier (required)
     * @param itemId   The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 200)
     *         or Todo item is invalid (status code 400)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#updateItemById
     */
    public TodoItem updateItemById(String listId,
            String itemId,
            TodoItem todoItem);

    /**
     * Changes the state of the specified list items
     *
     * @param listId      The Todo list unique identifier (required)
     * @param state       The Todo item state (required)
     * @param requestBody (optional)
     * @return Todo items updated (status code 204)
     *         or Update request is invalid (status code 400)
     * @see ListsApi#updateItemsStateByListId
     */
    public boolean updateItemsStateByListId(String listId,
            TodoState state,
            List<String> requestBody);

    /**
     * Updates a Todo list by unique identifier
     *
     * @param listId   The Todo list unique identifier (required)
     * @param todoList The Todo List (optional)
     * @return A Todo list result (status code 200)
     *         or Todo list is invalid (status code 400)
     * @see ListsApi#updateListById
     */
    public TodoList updateListById(String listId,
            TodoList todoList);

}
