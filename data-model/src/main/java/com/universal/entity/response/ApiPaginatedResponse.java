package com.universal.entity.response;

import com.google.common.collect.Lists;
import com.universal.entity.http.Pagination;

import java.util.List;

public class ApiPaginatedResponse<T> implements ApiResponse {
    private List<T> results;

    private Pagination pagination;

    public ApiPaginatedResponse() {
        this.results = Lists.newArrayList();
    }

    public ApiPaginatedResponse(List<T> resultList, Pagination pagination) {
        this.results = resultList;
        this.pagination = pagination;
    }

    public List<T> getResults() {
        return results;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
