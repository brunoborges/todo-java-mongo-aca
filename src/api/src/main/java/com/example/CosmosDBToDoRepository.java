package com.example;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;
import com.mongodb.client.MongoClient;

public class CosmosDBToDoRepository implements ToDoRepository {

    private MongoTemplate mongo;

    public CosmosDBToDoRepository(MongoClient mongoClient, String database) {
        this.mongo = new MongoTemplate(mongoClient, database);
    }

    @Override
    public TodoItem createItem(String listId, TodoItem todoItem) {
        todoItem.setListId(listId);
        if (todoItem.getState() == null) {
            todoItem.setState(TodoState.TODO);
        }

        if (todoItem.getDueDate() == null) {
            // Set due date to Two Days from now
            todoItem.setDueDate(OffsetDateTime.now().plusDays(2));
        }

        todoItem = mongo.insert(todoItem, "TodoItem");
        return todoItem;
    }

    @Override
    public TodoList createList(TodoList todoList) {
        todoList = mongo.insert(todoList, "TodoList");
        return todoList;
    }

    @Override
    public boolean deleteItemById(String listId, String itemId) {
        var query = new Query();
        query.addCriteria(Criteria.where("id").is(itemId));
        return mongo.remove(query, TodoItem.class, "TodoItem").getDeletedCount() > 0;
    }

    @Override
    public boolean deleteListById(String listId) {
        // Delete all items in the list
        var query = new Query();
        query.addCriteria(Criteria.where("listId").is(listId));
        mongo.remove(query, TodoItem.class, "TodoItem");

        // Delete the list
        query = new Query();
        query.addCriteria(Criteria.where("id").is(listId));
        return mongo.remove(query, TodoList.class, "TodoList").getDeletedCount() > 0;
    }

    @Override
    public TodoItem getItemById(String listId, String itemId) {
        return mongo.findById(itemId, TodoItem.class, "TodoItem");
    }

    @Override
    public List<TodoItem> getItemsByListId(String listId, BigDecimal top, BigDecimal skip) {
        var query = new Query();
        query.addCriteria(Criteria.where("listId").is(listId));
        if (top != null) {
            query.limit(top.intValue());
        }
        if (skip != null) {
            query.skip(skip.intValue());
        }
        return mongo.find(query, TodoItem.class, "TodoItem");
    }

    @Override
    public List<TodoItem> getItemsByListIdAndState(String listId, TodoState state, BigDecimal top, BigDecimal skip) {
        var query = new Query();
        query.addCriteria(Criteria.where("listId").is(listId));
        query.addCriteria(Criteria.where("state").is(state));
        if (top != null) {
            query.limit(top.intValue());
        }
        if (skip != null) {
            query.skip(skip.intValue());
        }
        return mongo.find(query, TodoItem.class, "TodoItem");
    }

    @Override
    public TodoList getListById(String listId) {
        return mongo.findById(listId, TodoList.class, "TodoList");
    }

    @Override
    public List<TodoList> getLists(BigDecimal top, BigDecimal skip) {
        var query = new Query();
        if (top != null) {
            query.limit(top.intValue());
        }
        if (skip != null) {
            query.skip(skip.intValue());
        }
        return mongo.find(query, TodoList.class, "TodoList");
    }

    @Override
    public TodoItem updateItemById(String listId, String itemId, TodoItem todoItem) {
        var query = new Query();
        query.addCriteria(Criteria.where("id").is(itemId));
        var update = new Update();
        update.set("name", todoItem.getName());
        update.set("description", todoItem.getDescription());
        update.set("dueDate", todoItem.getDueDate());
        update.set("state", todoItem.getState());

        if (todoItem.getState() == TodoState.DONE) {
            update.set("completedDate", OffsetDateTime.now());
        } else {
            update.unset("completedDate");
        }

        return mongo.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), TodoItem.class,
                "TodoItem");
    }

    @Override
    public boolean updateItemsStateByListId(String listId, TodoState state, List<String> requestBody) {
        var query = new Query();
        query.addCriteria(Criteria.where("listId").is(listId));
        query.addCriteria(Criteria.where("id").in(requestBody));

        var update = new Update();
        update.set("state", state);

        if (state == TodoState.DONE) {
            update.set("completedDate", OffsetDateTime.now());
        }

        return mongo.updateMulti(query, update, TodoItem.class,
                "TodoItem").getModifiedCount() > 0;
    }

    @Override
    public TodoList updateListById(String listId, TodoList todoList) {
        if (todoList == null) {
            return null;
        }
        var query = new Query(Criteria.where("id").is(listId));
        var update = new Update();
        update.set("name", todoList.getName());
        update.set("description", todoList.getDescription());
        var result = mongo.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), TodoList.class,
                "TodoList");
        return result;
    }

}
