package com.universal.entity.http;

import org.hibernate.validator.constraints.NotEmpty;

public class PaginatedRequestWithId extends PaginatedRequest {

    @NotEmpty(message = "id is required")
    private String entityId;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
