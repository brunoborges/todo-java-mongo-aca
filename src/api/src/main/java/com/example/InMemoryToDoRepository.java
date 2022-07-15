package com.example;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;

public class InMemoryToDoRepository implements ToDoRepository {

    private static final TodoList DUMMY_LIST = new TodoList();

    private Map<TodoList, Set<TodoItem>> todoListItems = Collections.synchronizedSortedMap(new TreeMap<>((TodoList o1, TodoList o2) -> {
        if (o1 != null && o2 != null) {
            var o1Id = o1.getId();
            var o2Id = o2.getId();

            if (o1Id != null && o2Id != null) {
                var intId1 = Integer.valueOf(o1Id);
                var intId2 = Integer.valueOf(o2Id);
                return intId1.compareTo(intId2);
            }
        }

        return 0;
    }));

    private AtomicInteger nextListId = new AtomicInteger(0);
    private AtomicInteger nextItemId = new AtomicInteger(0);

    private Optional<TodoList> internal_getListById(String listId) {
        return todoListItems.keySet().stream()
                .filter(list -> list.getId().equals(listId))
                .findFirst();
    }

    private Optional<TodoItem> internal_getItemById(String listId, String itemId) {
        var list = internal_getListById(listId);
        if (list.isPresent()) {
            var items = todoListItems.get(list.get());
            return items.stream()
                    .filter(item -> item.getId().equals(itemId))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public TodoItem createItem(String listId, TodoItem todoItem) {
        var todoListOpt = internal_getListById(listId);
        if (todoListOpt.isEmpty()) {
            return null;
        }
        var todoList = todoListOpt.get();

        todoItem.setId(String.valueOf(nextItemId.getAndIncrement()));
        todoItem.setListId(listId);

        if (todoItem.getState() == null) {
            todoItem.setState(TodoState.TODO);
        }

        if (todoItem.getDueDate() == null) {
            // Set due date to Two Days from now
            todoItem.setDueDate(OffsetDateTime.now().plusDays(2));
        }

        var listOfItems = todoListItems.get(todoList);
        listOfItems.add(todoItem);

        return todoItem;
    }

    @Override
    public TodoList createList(TodoList todoList) {
        todoList.setId(String.valueOf(nextListId.getAndIncrement()));
        todoListItems.put(todoList, new TreeSet<TodoItem>((TodoItem o1, TodoItem o2) -> {
            if (o1 != null && o2 != null) {
                var o1Id = o1.getId();
                var o2Id = o2.getId();

                if (o1Id != null && o2Id != null) {
                    var intId1 = Integer.valueOf(o1Id);
                    var intId2 = Integer.valueOf(o2Id);
                    return intId1.compareTo(intId2);
                }
            }

            return 0;
        }));
        return todoList;
    }

    @Override
    public boolean deleteItemById(String listId, String itemId) {
        var item = internal_getItemById(listId, itemId);

        if (item.isPresent()) {
            var list = internal_getListById(listId).orElse(DUMMY_LIST);
            var listOfItems = todoListItems.get(list);
            listOfItems.remove(item.get());
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteListById(String listId) {
        var todoListOpt = internal_getListById(listId);

        if (todoListOpt.isPresent()) {
            todoListItems.remove(todoListOpt.get());
            return true;
        }

        return false;
    }

    @Override
    public TodoItem getItemById(String listId, String itemId) {
        return internal_getItemById(listId, itemId).orElse(null);
    }

    @Override
    public List<TodoItem> getItemsByListId(String listId, BigDecimal top, BigDecimal skip) {
        var list = internal_getListById(listId).orElse(DUMMY_LIST);
        var listOfItems = todoListItems.get(list);
        if (listOfItems == null) {
            return Collections.emptyList();
        }
        return List.copyOf(listOfItems);
    }

    @Override
    public List<TodoItem> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top, BigDecimal skip) {
        var list = internal_getListById(listId).orElse(DUMMY_LIST);
        return todoListItems.get(list).stream().filter(todo -> todo.getState() == state).toList();
    }

    @Override
    public TodoList getListById(String listId) {
        return internal_getListById(listId).orElse(null);
    }

    @Override
    public List<TodoList> getLists(BigDecimal top, BigDecimal skip) {
        var lists = List.copyOf(todoListItems.keySet());

        // Skip x elements
        if (skip != null) {
            lists = lists.subList(Math.min(skip.intValue(), lists.size()), lists.size());
        }

        // Limit results to 'top'
        if (top != null) {
            lists = lists.subList(0, Math.min(top.intValue(), lists.size()));
        }

        return lists;
    }

    @Override
    public TodoItem updateItemById(String listId, String itemId, TodoItem todoItem) {
        var todoListOpt = internal_getListById(listId);
        if (todoListOpt.isEmpty()) {
            return null;
        }

        var todoList = todoListOpt.get();
        var items = todoListItems.get(todoList);
        var itemOpt = items.stream().filter(item -> item.getId().equals(itemId)).findFirst();

        var wrapper = new Object() {
            TodoItem value = null;
        };

        itemOpt.ifPresent(item -> {
            wrapper.value = item;
            if (todoItem.getDueDate() != null) {
                item.setDueDate(todoItem.getDueDate());
            }
            if (todoItem.getCompletedDate() != null) {
                item.setCompletedDate(todoItem.getCompletedDate());
            }
            if (todoItem.getName() != null) {
                item.setName(todoItem.getName());
            }
            if (todoItem.getDescription() != null) {
                item.setDescription(todoItem.getDescription());
            }
            if (item.getState() != null) {
                item.setState(todoItem.getState());
                if (item.getState() == TodoState.DONE) {
                    item.setCompletedDate(OffsetDateTime.now());
                }
            }
        });

        return wrapper.value;
    }

    @Override
    public boolean updateItemsStateByListId(String listId, TodoState state, List<String> requestBody) {
        var list = internal_getListById(listId).orElse(DUMMY_LIST);
        var listOfItems = todoListItems.get(list);
        if (listOfItems == null) {
            return false;
        }

        listOfItems.forEach(item -> {
            if (requestBody.contains(item.getId())) {
                item.setState(state);

                if (state == TodoState.DONE) {
                    item.setCompletedDate(OffsetDateTime.now());
                }
            }
        });

        return true;
    }

    @Override
    public TodoList updateListById(String listId, TodoList updatedTodoList) {
        var todoListOpt = internal_getListById(listId);
        if (todoListOpt.isEmpty()) {
            return null;
        }

        var todoList = todoListOpt.get();
        todoList.setName(updatedTodoList.getName());
        todoList.setDescription(updatedTodoList.getDescription());

        return todoList;
    }

}
