package com.woa.dataClasses;

import java.io.Serializable;

public class Order implements Serializable {
    int items, orderNo;
    String status;
    long time;
    String products;
    String orderKey;

    public Order() {
    }

    public Order(int items, int orderNo, String status, long time, String products, String orderKey)
    {
        this.items = items;
        this.orderNo = orderNo;
        this.status = status;
        this.time = time;
        this.products = products;
        this.orderKey = orderKey;
    }

    public int getItems() {
        return items;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public String getOrderKey() {
        return orderKey;
    }

    /*
    public String getProducts() {
        return products;
    }
*/

    /*
    class Products {

        String name, quantity;

        public Products() {
        }

        public Products(String name, String quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getQuantity() {
            return quantity;
        }
    }
*/
}
