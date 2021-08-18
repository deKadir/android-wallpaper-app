package com.dekaapps.breakingbadwallpapers;

public class ImageModel {
 private String imageLink;

    public ImageModel(String imageLink) {
        this.imageLink = imageLink;
    }

    public ImageModel() {
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
