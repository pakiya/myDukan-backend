package com.universal.db.manager;

import com.universal.db.dao.GroupDao;
import com.universal.db.dao.ProductDao;
import com.universal.entity.http.product.ProductEntity;
import com.universal.entity.response.group.Group;
import com.universal.search.entity.ProductESEntity;
import com.universal.search.service.ESProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by pankaj.
 */
@Service
public class PersistanceManager {

    private static final Logger logger = LoggerFactory.getLogger(PersistanceManager.class);

    /**
     * Product dao for mysql querys
     */
    @Autowired
    private ProductDao productDao;

    /**
     * Group dao for mysql querys
     */
    @Autowired
    private GroupDao groupDao;

    /**
     * Elastic search service class
     */
    @Autowired
    ESProductService esProductService;


    /**
     * product Create and update.
     * product is putting in elastic search database.
     * @param productEntity
     */
    public void upsetProduct(ProductEntity productEntity) {
        try {
            productDao.begin();
            productDao.upsetProduct(productEntity);
            ProductESEntity entity = new ProductESEntity(productEntity);
            esProductService.upsert(entity);
        } catch (Exception ex) {
            logger.error("Exception occured", ex);
            productDao.rollback();
            throw ex;
        }
    }

    /**
     * Insert product in ES
     * @param productEntity
     */
    public void upsetProductTOES(ProductEntity productEntity) {
        ProductESEntity entity = new ProductESEntity(productEntity);
        esProductService.upsert(entity);
    }

    /**
     * Fetch product details with product id
     * @param id
     * return product entity object
     * @return
     */
    public ProductEntity fetchProduct(String id) {
        return productDao.fetchProduct(id);
    }

    /**
     * Insert group.
     * @param group
     */
    public void upsetGroup(Group group) {
        groupDao.upsetGroup(group);
    }

    /**
     * Fetch group dateils with group name
     * @param name
     * @return Group object
     */
    public Group fetchGroup(String name) {
        return groupDao.fetchGroup(name);
    }

    /**
     * Fetch List of group with pagination
     * @param start
     * @param count
     * @return list of product entity
     */
    public List<ProductEntity> fetchGroupList(int start, int count) {
        return groupDao.fetchGroupList(start, count);
    }
}
