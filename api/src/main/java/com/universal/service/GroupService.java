package com.universal.service;

import com.universal.db.manager.PersistanceManager;
import com.universal.entity.http.PaginatedRequest;
import com.universal.entity.http.Pagination;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.entity.response.ApiSuccessResponse;
import com.universal.entity.response.group.Group;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import com.universal.service.helper.http.GoogleSheetAPI;
import com.universal.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private PersistanceManager persistanceManager;

    @Autowired
    GoogleSheetAPI googleSheetAPI;

    /**
     * Fetch all group list whit pagination call.
     * @param paginatedRequest
     * @return
     */
    public ApiPaginatedResponse<ProductEntity> fetchGroups(PaginatedRequest paginatedRequest) {

        if (paginatedRequest == null) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }

        Pagination pagination = paginatedRequest.getPagination();

        List<ProductEntity> productEntityList = persistanceManager.fetchGroupList(pagination.getStart(),
                pagination.getCount());
        pagination.setCount(productEntityList.size());
        ApiPaginatedResponse<ProductEntity> response = new ApiPaginatedResponse<ProductEntity>(productEntityList, pagination);
        return response;
    }

    /**
     * Crate group after check validation.
     *
     * @param group
     * @return
     */
    public ApiSuccessResponse createGroup(Group group) {
        if (group == null) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        if (Util.isNullOrEmpty(group.getName())) {
            throw new ApiServiceException(ServiceExceptionCodes.TITLE_NULL_REQUEST);
        }
        persistanceManager.upsetGroup(group);
        return new ApiSuccessResponse("Ok");
    }
}