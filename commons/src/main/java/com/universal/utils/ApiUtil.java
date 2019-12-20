package com.universal.utils;

//import com.universal.entity.http.Pagination;

public class ApiUtil {

    /*public static void validateRequest(BaseEntity request) {
        List<String> errorMessages = request.validate();
        if (!isCollectionNullOrEmpty(errorMessages)) {
            logger.error(errorMessages.toString());
            throw new ServiceValidationException(errorMessages.toString());
        }
    }*/

    /*public static Pagination validateAndClean(Pagination pagination, int defaultCount) {
        if (pagination == null) {
            pagination = new Pagination(0, defaultCount);
        }

        if (pagination.getCount() > defaultCount) {
            pagination.setCount(defaultCount);
        }
        if (pagination.getCount() == 0)
            pagination.setCount(defaultCount);
        return pagination;
    }*/
}
