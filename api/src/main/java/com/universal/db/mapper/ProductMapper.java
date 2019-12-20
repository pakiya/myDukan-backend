package com.universal.db.mapper;

import com.universal.entity.http.product.ProductEntity;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultSetMapper<ProductEntity> {
    @Override
    public ProductEntity map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        ProductEntity entity = new ProductEntity();
        entity.setId(r.getString("id"));
        entity.setName(r.getString("name"));
        entity.setModule(r.getString("module"));
        entity.setSerialNo(r.getString("serial_no"));
        entity.setGroupId(r.getString("group_id"));
        entity.setPrice(r.getInt("price"));
        return entity;
    }
}
