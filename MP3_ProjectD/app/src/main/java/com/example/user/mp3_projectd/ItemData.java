package com.example.user.mp3_projectd;

public class ItemData {
    int imgAlbumart;
    String txtTitle, txtSubTitle, txtDuration;

    public ItemData(int imgAlbumart, String txtTitle, String txtSubTitle, String txtDuration) {
        this.imgAlbumart = imgAlbumart;
        this.txtTitle = txtTitle;
        this.txtSubTitle = txtSubTitle;
        this.txtDuration = txtDuration;
    }

    public int getImgAlbumart() {
        return imgAlbumart;
    }

    public void setImgAlbumart(int imgAlbumart) {
        this.imgAlbumart = imgAlbumart;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtSubTitle() {
        return txtSubTitle;
    }

    public void setTxtSubTitle(String txtSubTitle) {
        this.txtSubTitle = txtSubTitle;
    }

    public String getTxtDuration() {
        return txtDuration;
    }

    public void setTxtDuration(String txtDuration) {
        this.txtDuration = txtDuration;
    }
}
