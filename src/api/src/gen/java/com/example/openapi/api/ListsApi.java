/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.openapi.api;

import java.math.BigDecimal;
import java.util.List;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "lists", description = "the lists API")
public interface ListsApi {

    default ListsApiDelegate getDelegate() {
        return new ListsApiDelegate() {};
    }

    /**
     * POST /lists/{listId}/items : Creates a new Todo item within a list
     *
     * @param listId The Todo list unique identifier (required)
     * @param todoItem The Todo Item (optional)
     * @return A Todo item result (status code 201)
     *         or Todo list not found (status code 404)
     */
    @Operation(
        operationId = "createItem",
        summary = "Creates a new Todo item within a list",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "201", description = "A Todo item result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))
            }),
            @ApiResponse(responseCode = "404", description = "Todo list not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/lists/{listId}/items",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TodoItem> createItem(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "TodoItem", description = "The Todo Item") @Valid @RequestBody(required = false) TodoItem todoItem
    ) {
        return getDelegate().createItem(listId, todoItem);
    }


    /**
     * POST /lists : Creates a new Todo list
     *
     * @param todoList The Todo List (optional)
     * @return A Todo list result (status code 201)
     *         or Invalid request schema (status code 400)
     */
    @Operation(
        operationId = "createList",
        summary = "Creates a new Todo list",
        tags = { "Lists" },
        responses = {
            @ApiResponse(responseCode = "201", description = "A Todo list result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoList.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request schema")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/lists",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TodoList> createList(
        @Parameter(name = "TodoList", description = "The Todo List") @Valid @RequestBody(required = false) TodoList todoList
    ) {
        return getDelegate().createList(todoList);
    }


    /**
     * DELETE /lists/{listId}/items/{itemId} : Deletes a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return Todo item deleted successfully (status code 204)
     *         or Todo list or item not found (status code 404)
     */
    @Operation(
        operationId = "deleteItemById",
        summary = "Deletes a Todo item by unique identifier",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Todo item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo list or item not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/lists/{listId}/items/{itemId}"
    )
    default ResponseEntity<Void> deleteItemById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "itemId", description = "The Todo list unique identifier", required = true) @PathVariable("itemId") String itemId
    ) {
        return getDelegate().deleteItemById(listId, itemId);
    }


    /**
     * DELETE /lists/{listId} : Deletes a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return Todo list deleted successfully (status code 204)
     *         or Todo list not found (status code 404)
     */
    @Operation(
        operationId = "deleteListById",
        summary = "Deletes a Todo list by unique identifier",
        tags = { "Lists" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Todo list deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo list not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/lists/{listId}"
    )
    default ResponseEntity<Void> deleteListById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId
    ) {
        return getDelegate().deleteListById(listId);
    }


    /**
     * GET /lists/{listId}/items/{itemId} : Gets a Todo item by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param itemId The Todo list unique identifier (required)
     * @return A Todo item result (status code 200)
     *         or Todo list or item not found (status code 404)
     */
    @Operation(
        operationId = "getItemById",
        summary = "Gets a Todo item by unique identifier",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A Todo item result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))
            }),
            @ApiResponse(responseCode = "404", description = "Todo list or item not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/lists/{listId}/items/{itemId}",
        produces = { "application/json" }
    )
    default ResponseEntity<TodoItem> getItemById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "itemId", description = "The Todo list unique identifier", required = true) @PathVariable("itemId") String itemId
    ) {
        return getDelegate().getItemById(listId, itemId);
    }


    /**
     * GET /lists/{listId}/items : Gets Todo items within the specified list
     *
     * @param listId The Todo list unique identifier (required)
     * @param top The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo items (status code 200)
     *         or Todo list not found (status code 404)
     */
    @Operation(
        operationId = "getItemsByListId",
        summary = "Gets Todo items within the specified list",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An array of Todo items", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))
            }),
            @ApiResponse(responseCode = "404", description = "Todo list not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/lists/{listId}/items",
        produces = { "application/json" }
    )
    default ResponseEntity<List<TodoItem>> getItemsByListId(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "top", description = "The max number of items to returns in a result") @Valid @RequestParam(value = "top", required = false) BigDecimal top,
        @Parameter(name = "skip", description = "The number of items to skip within the results") @Valid @RequestParam(value = "skip", required = false) BigDecimal skip
    ) {
        return getDelegate().getItemsByListId(listId, top, skip);
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
     */
    @Operation(
        operationId = "getItemsByListIdAndState",
        summary = "Gets a list of Todo items of a specific state",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An array of Todo items", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))
            }),
            @ApiResponse(responseCode = "404", description = "Todo list or item not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/lists/{listId}/items/state/{state}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<TodoItem>> getItemsByListIdAndState(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "state", description = "The Todo item state", required = true) @PathVariable("state") TodoState state,
        @Parameter(name = "top", description = "The max number of items to returns in a result") @Valid @RequestParam(value = "top", required = false) BigDecimal top,
        @Parameter(name = "skip", description = "The number of items to skip within the results") @Valid @RequestParam(value = "skip", required = false) BigDecimal skip
    ) {
        return getDelegate().getItemsByListIdAndState(listId, state, top, skip);
    }


    /**
     * GET /lists/{listId} : Gets a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @return A Todo list result (status code 200)
     *         or Todo list not found (status code 404)
     */
    @Operation(
        operationId = "getListById",
        summary = "Gets a Todo list by unique identifier",
        tags = { "Lists" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A Todo list result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoList.class))
            }),
            @ApiResponse(responseCode = "404", description = "Todo list not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/lists/{listId}",
        produces = { "application/json" }
    )
    default ResponseEntity<TodoList> getListById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId
    ) {
        return getDelegate().getListById(listId);
    }


    /**
     * GET /lists : Gets an array of Todo lists
     *
     * @param top The max number of items to returns in a result (optional)
     * @param skip The number of items to skip within the results (optional)
     * @return An array of Todo lists (status code 200)
     */
    @Operation(
        operationId = "getLists",
        summary = "Gets an array of Todo lists",
        tags = { "Lists" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An array of Todo lists", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoList.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/lists",
        produces = { "application/json" }
    )
    default ResponseEntity<List<TodoList>> getLists(
        @Parameter(name = "top", description = "The max number of items to returns in a result") @Valid @RequestParam(value = "top", required = false) BigDecimal top,
        @Parameter(name = "skip", description = "The number of items to skip within the results") @Valid @RequestParam(value = "skip", required = false) BigDecimal skip
    ) {
        return getDelegate().getLists(top, skip);
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
     */
    @Operation(
        operationId = "updateItemById",
        summary = "Updates a Todo item by unique identifier",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A Todo item result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))
            }),
            @ApiResponse(responseCode = "400", description = "Todo item is invalid"),
            @ApiResponse(responseCode = "404", description = "Todo list or item not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/lists/{listId}/items/{itemId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TodoItem> updateItemById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "itemId", description = "The Todo list unique identifier", required = true) @PathVariable("itemId") String itemId,
        @Parameter(name = "TodoItem", description = "The Todo Item") @Valid @RequestBody(required = false) TodoItem todoItem
    ) {
        return getDelegate().updateItemById(listId, itemId, todoItem);
    }


    /**
     * PUT /lists/{listId}/items/state/{state} : Changes the state of the specified list items
     *
     * @param listId The Todo list unique identifier (required)
     * @param state The Todo item state (required)
     * @param requestBody  (optional)
     * @return Todo items updated (status code 204)
     *         or Update request is invalid (status code 400)
     */
    @Operation(
        operationId = "updateItemsStateByListId",
        summary = "Changes the state of the specified list items",
        tags = { "Items" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Todo items updated"),
            @ApiResponse(responseCode = "400", description = "Update request is invalid")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/lists/{listId}/items/state/{state}",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateItemsStateByListId(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "state", description = "The Todo item state", required = true) @PathVariable("state") TodoState state,
        @Parameter(name = "request_body", description = "") @Valid @RequestBody(required = false) List<String> requestBody
    ) {
        return getDelegate().updateItemsStateByListId(listId, state, requestBody);
    }


    /**
     * PUT /lists/{listId} : Updates a Todo list by unique identifier
     *
     * @param listId The Todo list unique identifier (required)
     * @param todoList The Todo List (optional)
     * @return A Todo list result (status code 200)
     *         or Todo list is invalid (status code 400)
     */
    @Operation(
        operationId = "updateListById",
        summary = "Updates a Todo list by unique identifier",
        tags = { "Lists" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A Todo list result", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TodoList.class))
            }),
            @ApiResponse(responseCode = "400", description = "Todo list is invalid")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/lists/{listId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<TodoList> updateListById(
        @Parameter(name = "listId", description = "The Todo list unique identifier", required = true) @PathVariable("listId") String listId,
        @Parameter(name = "TodoList", description = "The Todo List") @Valid @RequestBody(required = false) TodoList todoList
    ) {
        return getDelegate().updateListById(listId, todoList);
    }

}
