package com.example.user.recylerviewtestt;

import android.widget.ImageView;

public class MainData {

    int imgLayout;
    String txtNameOne;
    String txtNameTwo;

    public MainData(int imgLayout, String txtNameOne, String txtNameTwo) {
        this.imgLayout = imgLayout;
        this.txtNameOne = txtNameOne;
        this.txtNameTwo = txtNameTwo;
    }

    public int getImgLayout() {
        return imgLayout;
    }

    public String getTxtNameOne() {
        return txtNameOne;
    }

    public String getTxtNameTwo() {
        return txtNameTwo;
    }

    public void setImgLayout(int imgLayout) {
        this.imgLayout = imgLayout;
    }

    public void setTxtNameOne(String txtNameOne) {
        this.txtNameOne = txtNameOne;
    }

    public void setTxtNameTwo(String txtNameTwo) {
        this.txtNameTwo = txtNameTwo;
    }

    //     int imgProfile;
//     String txtName;
//     String txtContent;
//
//    public MainData(int imgProfile, String txtName, String txtContent) {
//        this.imgProfile = imgProfile;
//        this.txtName = txtName;
//        this.txtContent = txtContent;
//    }
//
//    public int getImgProfile() {
//        return imgProfile;
//    }
//
//    public String getTxtName() {
//        return txtName;
//    }
//
//    public String getTxtContent() {
//        return txtContent;
//    }
//
//    public void setImgProfile(int imgProfile) {
//        this.imgProfile = imgProfile;
//    }
//
//    public void setTxtName(String txtName) {
//        this.txtName = txtName;
//    }
//
//    public void setTxtContent(String txtContent) {
//        this.txtContent = txtContent;
//    }
}
