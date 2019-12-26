package com.example.user.exercise_jic11_1;

public class ImageData {
    Integer ImageID;
    String imageName;



    public ImageData(Integer imageID) {
        this.ImageID = imageID;
    }
    public ImageData(Integer imageID, String imageName) {
        this.ImageID = imageID;
        this.imageName = imageName;
    }

    public Integer getImageID() {
        return ImageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageID(Integer imageID) {
        ImageID = imageID;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
