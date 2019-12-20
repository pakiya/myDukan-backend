package com.universal.search.service.impl;

import com.universal.constants.CommonConstants;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import com.universal.search.constants.SearchConstants;
import com.universal.search.entity.ProductESEntity;
import com.universal.search.repositories.ESProductRepository;
import com.universal.search.service.ESProductService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
@Profile(CommonConstants.DEFAULT_PROFILE)
public class ESProductServiceImpl implements ESProductService {

    private static final Logger log = LoggerFactory.getLogger(ESProductService.class);

    @Autowired
    private ESProductRepository repository;

    @Autowired
    private JestClient client;

    /**
     * Insert product data in the elastic search
     * @param product
     */
    @Override
    public void upsert(ProductESEntity product) {
        repository.save(product);
    }

    /**
     * Detale data from elatic search
     * @param id
     */
    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    /**
     * Search data whit the query from elastic search database
     * @param queryText
     * @param from
     * @param size
     * @return
     */
    @Override
    public List<ProductESEntity> searchProduct(String queryText, int from, int size) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            queryText = queryText + "*";
            searchSourceBuilder.query(new SimpleQueryStringBuilder(queryText)).from(from).size(size);
            Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(SearchConstants.PRODUCT_INDEX_NAME).build();
            JestResult result = client.execute(search);
            return result.getSourceAsObjectList(ProductESEntity.class);
        } catch (Exception e) {
            log.error("Error occurred while search", e);
            throw new ApiServiceException(e, ServiceExceptionCodes.SEARCH_INTERNAL_ERROR);
        }
    }

    /**
     * Clar all data from elastic search server
     */
    @Override
    public void clearCache() {
        try {
            DeleteIndex deleteIndex = new DeleteIndex.Builder(SearchConstants.PRODUCT_INDEX_NAME).build();
            client.execute(deleteIndex);
        } catch (Exception e) {
            log.error("Error occurred while search", e);
            throw new ApiServiceException(e, ServiceExceptionCodes.SEARCH_INTERNAL_ERROR);
        }
    }
}
