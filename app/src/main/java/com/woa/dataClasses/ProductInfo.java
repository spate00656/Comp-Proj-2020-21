package com.woa.dataClasses;

public class ProductInfo {

    String productName, quantity;

    public ProductInfo(){}

    public ProductInfo(String productName, String quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getQuantity() {
        return quantity;
    }
}
