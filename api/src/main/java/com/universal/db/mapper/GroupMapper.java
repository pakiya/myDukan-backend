package com.universal.db.mapper;

import com.universal.entity.response.group.Group;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements ResultSetMapper<Group> {
    @Override
    public Group map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Group group = new Group();
        group.setName(r.getString("name"));
        group.setDescription(r.getString("description"));
        group.setActive(r.getBoolean("isActive"));
        return group;
    }
}
