package com.example.user.spinnertest;

public class ImageData {
    Integer imageID;
    String imageName;

    public ImageData(Integer imageID, String imageName) {
        this.imageID = imageID;
        this.imageName = imageName;
    }

    public Integer getImageID() {
        return imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public void setSpinnerText(String imageName) {
        this.imageName = imageName;
    }
}
