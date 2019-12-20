package com.universal.db.dao;

import com.universal.constants.CommonConstants;
import com.universal.db.mapper.GroupMapper;
import com.universal.db.mapper.ProductGroupByMapper;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.group.Group;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.List;

@UseStringTemplate3StatementLocator()
public interface GroupDao extends Transactional<GroupDao> {

    /***
     * Insert and update group date- mysql query
     * @param group
     */
    @SqlUpdate(CommonConstants.QueryConstants.UPSET_GROUP)
    void upsetGroup(@BindBean Group group);

    /**
     * Fetch group details by groupname
     * @param name
     * @return
     */
    @SqlQuery(CommonConstants.QueryConstants.FETCH_GROUP)
    @Mapper(GroupMapper.class)
    Group fetchGroup(@Bind("name") String name);

    /**
     * Fetch group list with pagination.
     * @param start
     * @param count
     * @return
     */
    @SqlQuery(CommonConstants.QueryConstants.FETCH_GROUP_LIST)
    @Mapper(ProductGroupByMapper.class)
    List<ProductEntity> fetchGroupList(@Bind("start") int start, @Bind("count") int count);
}
