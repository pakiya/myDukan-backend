package com.universal.db.dao;

import com.universal.constants.CommonConstants;
import com.universal.db.mapper.ConfigMapper;
import com.universal.entity.http.config.ConfigEntity;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ConfigDao {

    @SqlQuery(CommonConstants.QueryConstants.FETCH_CONFIG_SQL)
    @Mapper(ConfigMapper.class)
    List<ConfigEntity> readConfigMap();
}
