package com.universal.db.mapper;

import com.universal.entity.http.config.ConfigEntity;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigMapper implements ResultSetMapper<ConfigEntity> {
    @Override
    public ConfigEntity map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setActive(rs.getBoolean("is_active"));
        configEntity.setPropertyName(rs.getString("property_name"));
        configEntity.setValue(rs.getObject("value"));
        return configEntity;
    }
}
