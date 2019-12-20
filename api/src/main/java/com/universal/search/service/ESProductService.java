package com.universal.search.service;

import com.universal.search.entity.ProductESEntity;

import java.util.List;

public interface ESProductService {
    void upsert(ProductESEntity product);

    void delete(String id);

    List<ProductESEntity> searchProduct(String query, int from, int size);

    void clearCache();
}
