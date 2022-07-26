package com.example.openapi.api;

import java.util.Optional;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
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
