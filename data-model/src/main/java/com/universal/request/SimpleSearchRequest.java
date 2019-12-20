package com.universal.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universal.entity.http.PaginatedRequest;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleSearchRequest extends PaginatedRequest {

    @NotEmpty(message = "Search query is required")
    @Size(max = 100, message = "Query can not exceed 100")
    String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
