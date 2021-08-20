package com.woa.dataClasses;

import java.io.Serializable;

public class BandProduct implements Serializable {

    String code, name, description, pictureUri,date;


    public BandProduct() {
    }

    public BandProduct(String code, String name, String description, String pictureUri, String date) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.pictureUri = pictureUri;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
