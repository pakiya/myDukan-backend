package com.universal.entity.factory;

import com.universal.constants.CommonConstants;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.group.Group;
import com.universal.request.ProductRequst;
import com.universal.utils.Util;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactory {

    public ProductEntity createProduct(ProductRequst productEntity) {
        ProductEntity entity = new ProductEntity();
        if (Util.isNullOrEmpty(productEntity.getId())) {
            entity.setId(Util.generateUniqueId(CommonConstants.SessionConstants.PRODUCT_ID_PREFIX,
                    CommonConstants.SessionConstants.ID_LENGTH));
        } else {
            entity.setId(productEntity.getId());
        }
        entity.setGroupId(productEntity.getGroupName());
        entity.setModule(productEntity.getModule());
        entity.setSerialNo(productEntity.getSerialNo());
        entity.setPrice(productEntity.getPrice());
        entity.setName(productEntity.getName());
        return entity;
    }

    public Group createGroup(String name, String description, boolean iActive) {
        Group group = new Group();
        group.setName(name);
        group.setActive(iActive);
        group.setDescription(description);
        return group;
    }
}
