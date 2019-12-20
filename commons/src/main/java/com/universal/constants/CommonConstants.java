package com.universal.constants;

public class CommonConstants {

    public static final long DEFAULT_API_TIMEOUT = 1000;
    public static final String DEFAULT_PROFILE = "default";
    public static final String USER_AGENT_KEY = "user-agent";


    public static class ErrorMessages {
        public static final String BAD_REQUEST = "Something is not right here! Please try again.";
        public static final String TITLE_NULL_REQUEST = "Title can't be null or empty!";
        public static final String INVALID_REQUEST = "Something is not right here!";
        public static final String INVALID_SHEET_PAGINATION = "Please enter start count < 1";
        public static final String INTERNAL_SYSTEM_ERROR = "We are unable to process your request at this moment. Please try again later";
    }

    public static class SessionConstants {
        public static final int ID_LENGTH = 36;
        public static final String PRODUCT_ID_PREFIX = "PID";
        public static final String IS_LOGGED_IN = "is_logged_in";
    }

    public static class QueryConstants {

        public static final String FETCH_CONFIG_SQL = "Select `property_name`,`value`,`is_active` from ApiConfig where is_active = 1";
        public static final String UPSERT_PRODUCT = "insert into Product (`id`,`name`,`module`,`serial_no`,`group_id`,`price`) values (:id,:name,:module,:serialNo,:groupId, :price) on duplicate key update name=:name, module=:module,serial_no=:serialNo,group_id=:groupId, price=:price";
        public static final String FETCH_PRODUCT = "select * from Product where id=:id";
        public static final String UPSET_GROUP = "insert into Groups (`name`,`description`,`isActive`) values (:name,:description,:active) on duplicate key update description=:description,isActive=:active";
        public static final String FETCH_GROUP = "SELECT * FROM Groups WHERE name=:name";
        public static final String FETCH_GROUP_LIST = "SELECT G.name as groupName, G.description, G.isActive,P.id,P.name,P.module,P.serial_no,(SELECT count(*) FROM Product P1 WHERE P1.group_id = G.name) as product_count, (SELECT sum(P2.price) FROM Product P2 WHERE P2.group_id = G.name) as price FROM Groups G join Product P on G.name=P.group_id group by P.group_id limit :start, :count";
    }

}
