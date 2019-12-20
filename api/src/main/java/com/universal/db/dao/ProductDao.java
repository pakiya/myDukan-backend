package com.universal.db.dao;

import com.universal.constants.CommonConstants;
import com.universal.db.mapper.ProductMapper;
import com.universal.entity.http.product.ProductEntity;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

@UseStringTemplate3StatementLocator()
public interface ProductDao extends Transactional<ProductDao> {

    /**
     * Insert and update product mysql query.
     * @param productEntity
     */
    @SqlUpdate(CommonConstants.QueryConstants.UPSERT_PRODUCT)
    void upsetProduct(@BindBean ProductEntity productEntity);

    /**
     * Fetch Product details my sql query
     * @param id
     * @return
     */
    @SqlQuery(CommonConstants.QueryConstants.FETCH_PRODUCT)
    @Mapper(ProductMapper.class)
    ProductEntity fetchProduct(@Bind("id") String id);
}
