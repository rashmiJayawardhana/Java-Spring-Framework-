package com.Java_Spring_Framework.Java_Spring_Framework.dto;

public class ProfileDTO {
    private String id;
    private String image;
    private Boolean status;

    public ProfileDTO(String id, String image, Boolean status) {
        this.id = id;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
