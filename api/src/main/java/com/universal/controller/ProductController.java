package com.universal.controller;

import com.universal.entity.http.PaginatedRequestWithId;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.entity.response.ApiSuccessResponse;
import com.universal.request.ProductRequst;
import com.universal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by pankaj.
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * Create Product
     * Create group if group is not exist.
     * gettign succes response then data is store elastic search database.
     *
     * @param productRequst
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiSuccessResponse createNewProduct(@RequestBody ProductRequst productRequst) {
        return productService.createProduct(productRequst);
    }

    /**
     * Update Product price
     * Update(change) the group of a product
     * * after update product is store elastic search database.
     *
     * @param productEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiSuccessResponse updateProduct(@RequestBody ProductRequst productEntity) {
        return productService.updateProduct(productEntity);
    }

    /**
     * Fetch  Google sheet data with pagination and spreadsheet Id's
     * Add Google sheet data in to the elastic search.
     *
     * @param paginatedRequest
     * @return
     */
    @RequestMapping(value = "/sheet", method = RequestMethod.POST)
    public ApiPaginatedResponse<ProductEntity> fetchGoogleSheet(@RequestBody PaginatedRequestWithId paginatedRequest) {
        return productService.fetchProductSheet(paginatedRequest);
    }

}
