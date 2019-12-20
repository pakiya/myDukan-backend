package com.universal.db.mapper;

import com.universal.entity.http.product.ProductEntity;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductGroupByMapper implements ResultSetMapper<ProductEntity> {
    @Override
    public ProductEntity map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        ProductEntity entity = new ProductEntity();
        entity.setGroupName(r.getString("groupName"));
        entity.setDescription(r.getString("description"));
        entity.setActive(r.getBoolean("isActive"));
        entity.setId(r.getString("id"));
        entity.setName(r.getString("name"));
        entity.setModule(r.getString("module"));
        entity.setSerialNo(r.getString("serial_no"));
        entity.setProductCount(r.getInt("product_count"));
        entity.setPrice(r.getInt("price"));
        return entity;
    }
}
