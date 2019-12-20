package com.universal.search.repositories;

import com.universal.search.entity.ProductESEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESProductRepository extends ElasticsearchRepository<ProductESEntity, String> {
}
