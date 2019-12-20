package com.universal.search.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.universal.entity.http.product.ProductEntity;
import com.universal.search.constants.SearchConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = SearchConstants.PRODUCT_INDEX_NAME, type = SearchConstants.PRODUCT_TYPE, replicas = 1, shards = 5)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductESEntity {
    @Id
    private String id;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String name;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String module;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String serialNo;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String groupNmae;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private int price;

    public ProductESEntity(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.module = productEntity.getModule();
        this.serialNo = productEntity.getSerialNo();
        this.groupNmae = productEntity.getGroupId();
        this.price = productEntity.getPrice();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getGroupNmae() {
        return groupNmae;
    }

    public void setGroupNmae(String groupNmae) {
        this.groupNmae = groupNmae;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
