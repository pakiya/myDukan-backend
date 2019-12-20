package com.universal.service;

import com.universal.db.manager.PersistanceManager;
import com.universal.entity.factory.ObjectFactory;
import com.universal.entity.http.PaginatedRequestWithId;
import com.universal.entity.http.Pagination;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.entity.response.ApiSuccessResponse;
import com.universal.entity.response.group.Group;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import com.universal.request.ProductRequst;
import com.universal.service.helper.http.GoogleSheetAPI;
import com.universal.utils.ExecutorUtil;
import com.universal.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * create by pankaj.
 */
@Service
public class ProductService {

    @Autowired
    private PersistanceManager persistanceManager;

    @Autowired
    GoogleSheetAPI googleSheetAPI;

    @Autowired
    private ObjectFactory objectFactory;


    /**
     * Create product and validate group is exist ot not if not then we are creating new group
     *
     * @param productEntity
     * @return
     */
    public ApiSuccessResponse createProduct(ProductRequst productEntity) {
        Group group = persistanceManager.fetchGroup(productEntity.getGroupName());
        if (group == null) {
            group = objectFactory.createGroup(productEntity.getGroupName(), null, true);
            persistanceManager.upsetGroup(group);
        }
        ProductEntity entity = objectFactory.createProduct(productEntity);
        persistanceManager.upsetProduct(entity);
        return new ApiSuccessResponse("OK");
    }

    /**
     * Updating product price and group with the same code.
     * validate group is exist ot not if not then we are creating new group.
     *
     * @param productEntity
     * @return
     */
    public ApiSuccessResponse updateProduct(ProductRequst productEntity) {
        if (Util.isNullOrEmpty(productEntity.getId())) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        Future<ProductEntity> productTask = ExecutorUtil.executeTask(() ->
                persistanceManager.fetchProduct(productEntity.getId()));
        Future<Group> groupTask = ExecutorUtil.executeTask(() ->
                persistanceManager.fetchGroup(productEntity.getGroupName()));
        ProductEntity product = ExecutorUtil.getResultFromTask(productTask, false);
        Group group = ExecutorUtil.getResultFromTask(groupTask, false);
        if (product == null) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        if (group == null) {
            group = objectFactory.createGroup(productEntity.getGroupName(), null, true);
            persistanceManager.upsetGroup(group);
        }
        ProductEntity entity = objectFactory.createProduct(productEntity);
        persistanceManager.upsetProduct(entity);
        return new ApiSuccessResponse("OK");
    }

    /**
     * Fetch Google sheet data from using google sheet api's
     * after fetching response we are mapping response objecct in data model
     * save sheet data in to the elastic search database.
     *
     * @param paginatedRequest
     * @return
     */
    public ApiPaginatedResponse<ProductEntity> fetchProductSheet(PaginatedRequestWithId paginatedRequest) {
        if (paginatedRequest == null) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        if (Util.isNullOrEmpty(paginatedRequest.getEntityId())) {
            throw new ApiServiceException(ServiceExceptionCodes.BAD_REQUEST);
        }
        Pagination pagination = paginatedRequest.getPagination();
        if (pagination.getStart() < 2) {
            throw new ApiServiceException(ServiceExceptionCodes.INVALID_SHEET_PAGINATION);
        }
        List<ProductEntity> productList = new ArrayList<>();
        try {
            List<List<Object>> sheetObject = googleSheetAPI.getSpreadSheetRecords(
                    paginatedRequest.getEntityId(), "product_listing!A" + pagination.getStart() + ":E" + pagination.getCount());
            for (int i = 0; i < sheetObject.size(); i++) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setName(String.valueOf(sheetObject.get(i).get(0)));
                productEntity.setModule(String.valueOf(sheetObject.get(i).get(1)));
                productEntity.setSerialNo(String.valueOf(sheetObject.get(i).get(2)));
                productEntity.setGroupId(String.valueOf(sheetObject.get(i).get(3)));
                productEntity.setPrice(Integer.parseInt(String.valueOf(sheetObject.get(i).get(4))));
                productList.add(productEntity);
                persistanceManager.upsetProductTOES(productEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pagination.setCount(productList.size());
        return new ApiPaginatedResponse<ProductEntity>(productList, pagination);
    }

}
