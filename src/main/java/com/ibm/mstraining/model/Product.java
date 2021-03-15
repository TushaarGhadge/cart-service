package com.ibm.mstraining.model;

import java.io.Serializable;

import javax.persistence.Column;

public class Product implements Serializable {

    private static final long serialVersionUID = -7304814269819778382L;

    private long itemId;

    private String name;
    
    @Column(name = "Description")
    private String desc;

    private double price;

    public Product() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [itemId=" + itemId + ", name=" + name + ", desc=" + desc + ", price=" + price + "]";
    }
}