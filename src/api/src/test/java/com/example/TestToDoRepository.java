package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

@SpringBootTest(classes = App.class)
@ActiveProfiles("dev")
public class TestToDoRepository {

    @Autowired
    private ToDoRepository repository;

    @Test
    public void createRetrieveUpdateDeleteTodoList() {
        // Retrieve all (should work!)
        var existingLists = repository.getLists(null, null);
        assertNotNull(existingLists);
        var countOfExistingLists = existingLists.size();

        // Create
        var todoList = new TodoList();
        todoList.setName("Foo");
        todoList.setDescription("FooDescription");
        todoList = repository.createList(todoList);

        assertNotNull(todoList);
        assertNotNull(todoList.getId());
        assertTrue(todoList.getId().length() > 0);
        assertEquals("Foo", todoList.getName());
        assertEquals("FooDescription", todoList.getDescription());

        // Retrieve
        var retrievedTodoList = repository.getListById(todoList.getId());
        assertNotNull(retrievedTodoList);
        assertEquals(todoList.getId(), retrievedTodoList.getId());

        // Retrieve List of TodoList
        var listOfLists = repository.getLists(null, null);
        assertNotNull(listOfLists);
        assertFalse(listOfLists.isEmpty());
        assertEquals(countOfExistingLists + 1, listOfLists.size());

        // Update
        var updatedTodo = new TodoList();
        updatedTodo.setName("Bar");
        updatedTodo.setDescription("BarDescription");
        updatedTodo = repository.updateListById(todoList.getId(), updatedTodo);

        assertEquals(todoList.getId(), updatedTodo.getId());
        assertEquals("Bar", updatedTodo.getName());
        assertEquals("BarDescription", updatedTodo.getDescription());

        // Delete
        var deleted = repository.deleteListById(updatedTodo.getId());
        assertTrue(deleted);
    }

    @Test
    public void createRetrieveUpdateDeleteItems() {
        // Create List
        var todoList = new TodoList();
        todoList.setName("Foo");
        todoList.setDescription("FooDescription");
        todoList = repository.createList(todoList);
        assertNotNull(todoList.getId());

        // Create Item
        var item = new TodoItem();
        item.setName("Testing");

        item = repository.createItem(todoList.getId(), item);

        assertNotNull(item);
        assertNotNull(item.getId());
        assertEquals(todoList.getId(), item.getListId());
        assertEquals(TodoState.TODO, item.getState());
        assertEquals("Testing", item.getName());

        // Retrieve item
        var retrievedItem = repository.getItemById(todoList.getId(), item.getId());
        assertNotNull(retrievedItem);
        assertEquals(item.getId(), retrievedItem.getId());
        assertEquals(item.getName(), retrievedItem.getName());
        assertEquals(item.getDescription(), retrievedItem.getDescription());
        assertEquals(item.getState(), retrievedItem.getState());
        assertEquals(item.getDueDate(), retrievedItem.getDueDate());
        assertEquals(item.getCompletedDate(), retrievedItem.getCompletedDate());
        assertEquals(item.getListId(), retrievedItem.getListId());

        assertNotNull(item.getDueDate());
        assertNull(item.getCompletedDate());

        // Retrieve items from list
        var items = repository.getItemsByListId(item.getListId(), null, null);
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
        assertEquals(item.getId(), items.get(0).getId());
        assertEquals(item.getName(), items.get(0).getName());
        assertEquals(item.getDescription(), items.get(0).getDescription());
        assertEquals(item.getState(), items.get(0).getState());
        assertEquals(item.getDueDate(), items.get(0).getDueDate());
        assertEquals(item.getCompletedDate(), items.get(0).getCompletedDate());
        assertEquals(item.getListId(), items.get(0).getListId());

        // Retrieve by state
        var todoItems = repository.getItemsByListIdAndState(item.getListId(), TodoState.TODO, null, null);
        assertNotNull(todoItems);
        assertFalse(todoItems.isEmpty());
        assertEquals(1, todoItems.size());

        // Update item
        var toUpdateItem = new TodoItem();
        var dueDate = OffsetDateTime.now().plusDays(2);
        toUpdateItem.setName("Updated Testing");
        toUpdateItem.setDescription("Updated Description");
        toUpdateItem.setState(TodoState.INPROGRESS);
        toUpdateItem.setDueDate(dueDate);

        var updatedItem = repository.updateItemById(item.getListId(), item.getId(), toUpdateItem);
        assertNotNull(updatedItem);
        assertEquals(item.getId(), updatedItem.getId());
        assertEquals(toUpdateItem.getName(), updatedItem.getName());
        assertEquals(toUpdateItem.getDescription(), updatedItem.getDescription());
        assertEquals(toUpdateItem.getState(), updatedItem.getState());
        assertEquals(toUpdateItem.getDueDate(), updatedItem.getDueDate());
        assertEquals(toUpdateItem.getCompletedDate(), updatedItem.getCompletedDate());
        assertNull(updatedItem.getCompletedDate());
        assertEquals(item.getListId(), updatedItem.getListId());

        // Update to DONE and check completed date is not null
        var toUpdateItemDone = new TodoItem();
        toUpdateItemDone.setState(TodoState.DONE);
        toUpdateItemDone = repository.updateItemById(item.getListId(), item.getId(), toUpdateItemDone);
        assertNotNull(toUpdateItemDone.getCompletedDate());
        assertEquals(TodoState.DONE, toUpdateItemDone.getState());

        // Update to INPROGRESS and check completed date is null
        toUpdateItemDone = new TodoItem();
        toUpdateItemDone.setState(TodoState.INPROGRESS);
        toUpdateItemDone = repository.updateItemById(item.getListId(), item.getId(), toUpdateItemDone);
        assertNull(toUpdateItemDone.getCompletedDate());
        assertEquals(TodoState.INPROGRESS, toUpdateItemDone.getState());

        // Update multiple items
        var toUpdateItems = Arrays.asList(updatedItem.getId());
        var updatedItems = repository.updateItemsStateByListId(item.getListId(), TodoState.DONE, toUpdateItems);
        assertNotNull(updatedItems);
        assertTrue(updatedItems);

        // Check updated item state is done and completed date is not null
        var updatedItemDone = repository.getItemById(updatedItem.getListId(), updatedItem.getId());
        assertNotNull(updatedItemDone);
        assertEquals(TodoState.DONE, updatedItemDone.getState());
        assertNotNull(updatedItemDone.getCompletedDate());

        // Delete item
        var deleted = repository.deleteItemById(todoList.getId(), item.getId());
        assertTrue(deleted);

        // Delete list
        var deletedList = repository.deleteListById(todoList.getId());
        assertTrue(deletedList);
    }

}
