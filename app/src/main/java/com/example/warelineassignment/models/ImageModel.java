package com.example.warelineassignment.models;

public class ImageModel {
    private String originalUrl, mediumUrl;
    private int id;

    public ImageModel(){}

    public ImageModel(String originalUrl, String mediumUrl, int id) {
        this.originalUrl = originalUrl;
        this.mediumUrl = mediumUrl;
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
