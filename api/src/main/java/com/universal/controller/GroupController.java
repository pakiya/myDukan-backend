package com.universal.controller;

import com.universal.entity.http.PaginatedRequest;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.ApiPaginatedResponse;
import com.universal.entity.response.ApiSuccessResponse;
import com.universal.entity.response.group.Group;
import com.universal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by pankaj.
 */
@RequestMapping("/group")
@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    /**
     * Creating a new group
     *
     * @param group
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiSuccessResponse createNewGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }


    /**
     * Fetch all group list with the price of all product
     * and we are getting product count as well.
     * Api call with pagination call
     *
     * @param paginatedRequest
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiPaginatedResponse<ProductEntity> fetchGroups(@RequestBody PaginatedRequest paginatedRequest) {
        return groupService.fetchGroups(paginatedRequest);
    }

}
