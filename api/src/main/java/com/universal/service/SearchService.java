package com.universal.service;

import com.mysql.jdbc.StringUtils;
import com.universal.entity.http.Pagination;
import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import com.universal.request.SimpleSearchRequest;
import com.universal.search.entity.ProductESEntity;
import com.universal.search.service.ESProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by pankaj.
 */
@Service
public class SearchService {

    @Autowired
    ESProductService productService;


    /**
     * Product search from elastic search.
     * @param simpleSearchRequest
     * @return
     */
    public ApiPaginatedResponse<ProductESEntity> searchClasses(SimpleSearchRequest simpleSearchRequest) {

        Pagination pagination = simpleSearchRequest.getPagination();
        if (pagination == null) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        if (!StringUtils.isNullOrEmpty(simpleSearchRequest.getQuery())) {
            List<ProductESEntity> esEntities = productService.searchProduct(simpleSearchRequest.getQuery(),
                    pagination.getStart(), pagination.getCount());

            pagination = new Pagination(0, esEntities.size());
            return new ApiPaginatedResponse<>(esEntities, pagination);

        } else {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
    }

}

