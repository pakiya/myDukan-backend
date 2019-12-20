package com.universal.controller;

import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.request.SimpleSearchRequest;
import com.universal.search.entity.ProductESEntity;
import com.universal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * Similar product search with name name
     * fetching product list with pagination.
     *
     * @param simpleSearchRequest
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ApiPaginatedResponse<ProductESEntity> simpleSearchClass(@RequestBody SimpleSearchRequest simpleSearchRequest) {
        return searchService.searchClasses(simpleSearchRequest);
    }
}
