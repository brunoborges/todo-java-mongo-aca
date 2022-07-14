package com.example.openapi.api;

import java.math.BigDecimal;
import java.util.List;
import com.example.openapi.model.TodoItem;
import com.example.openapi.model.TodoList;
import com.example.openapi.model.TodoState;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-14T21:31:08.805773106Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.simpleTodo.base-path:}")
public class ListsApiController implements ListsApi {

    private final ListsApiDelegate delegate;

    public ListsApiController(@Autowired(required = false) ListsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ListsApiDelegate() {});
    }

    @Override
    public ListsApiDelegate getDelegate() {
        return delegate;
    }

}
