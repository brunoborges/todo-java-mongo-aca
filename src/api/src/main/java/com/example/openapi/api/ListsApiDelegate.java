package com.example.openapi.api;

import java.math.BigDecimal;
import java.util.List;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link ListsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-14T21:48:55.739039250Z[Etc/UTC]")
public interface ListsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /lists/{listId}/items : Creates a new Todo item within a list
     *
     * @param listId The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 201)
     *         or Todo list not found (status code 404)
     * @see ListsApi#createItem
     */
    default ResponseEntity<TodoItem> createItem(String listId,
        TodoItem todoItem) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"listId\" : \"listId\", \"dueDate\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\", \"completedDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /lists : Creates a new Todo list
     *
     * @param todoList The Todo List (optional)
     * @return A Todo list result (status code 201)
     *         or Invalid request schema (status code 400)
     * @see ListsApi#createList
     */
    default ResponseEntity<TodoList> createList(TodoList todoList) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /lists/{listId}/items/{itemId} : Deletes a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return Todo item deleted successfully (status code 204)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#deleteItemById
     */
    default ResponseEntity<Void> deleteItemById(String listId,
        String itemId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /lists/{listId} : Deletes a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return Todo list deleted successfully (status code 204)
     *         or Todo list not found (status code 404)
     * @see ListsApi#deleteListById
     */
    default ResponseEntity<Void> deleteListById(String listId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /lists/{listId}/items/{itemId} : Gets a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return A Todo item result (status code 200)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#getItemById
     */
    default ResponseEntity<TodoItem> getItemById(String listId,
        String itemId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"listId\" : \"listId\", \"dueDate\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\", \"completedDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /lists/{listId}/items : Gets Todo items within the specified list
     *
     * @param listId The Todo list unique identifier (required)
     * @param top The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo items (status code 200)
     *         or Todo list not found (status code 404)
     * @see ListsApi#getItemsByListId
     */
    default ResponseEntity<List<TodoItem>> getItemsByListId(String listId,
        BigDecimal top,
        BigDecimal skip) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"listId\" : \"listId\", \"dueDate\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\", \"completedDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /lists/{listId}/items/state/{state} : Gets a list of Todo items of a specific state
     *
     * @param listId The Todo list unique identifier (required)
     * @param state The Todo item state (required)
     * @param top The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo items (status code 200)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#getItemsByListIdAndState
     */
    default ResponseEntity<List<TodoItem>> getItemsByListIdAndState(String listId,
        TodoState state,
        BigDecimal top,
        BigDecimal skip) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"listId\" : \"listId\", \"dueDate\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\", \"completedDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /lists/{listId} : Gets a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return A Todo list result (status code 200)
     *         or Todo list not found (status code 404)
     * @see ListsApi#getListById
     */
    default ResponseEntity<TodoList> getListById(String listId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /lists : Gets an array of Todo lists
     *
     * @param top The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo lists (status code 200)
     * @see ListsApi#getLists
     */
    default ResponseEntity<List<TodoList>> getLists(BigDecimal top,
        BigDecimal skip) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /lists/{listId}/items/{itemId} : Updates a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 200)
     *         or Todo item is invalid (status code 400)
     *         or Todo list or item not found (status code 404)
     * @see ListsApi#updateItemById
     */
    default ResponseEntity<TodoItem> updateItemById(String listId,
        String itemId,
        TodoItem todoItem) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"listId\" : \"listId\", \"dueDate\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\", \"completedDate\" : \"2000-01-23T04:56:07.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /lists/{listId}/items/state/{state} : Changes the state of the specified list items
     *
     * @param listId The Todo list unique identifier (required)
     * @param state The Todo item state (required)
     * @param requestBody  (optional)
     * @return Todo items updated (status code 204)
     *         or Update request is invalid (status code 400)
     * @see ListsApi#updateItemsStateByListId
     */
    default ResponseEntity<Void> updateItemsStateByListId(String listId,
        TodoState state,
        List<String> requestBody) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /lists/{listId} : Updates a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param todoList The Todo List (optional)
     * @return A Todo list result (status code 200)
     *         or Todo list is invalid (status code 400)
     * @see ListsApi#updateListById
     */
    default ResponseEntity<TodoList> updateListById(String listId,
        TodoList todoList) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"name\" : \"name\", \"description\" : \"description\", \"id\" : \"id\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
