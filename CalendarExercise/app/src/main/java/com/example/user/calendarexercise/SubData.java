package com.example.user.calendarexercise;

public class SubData {
    int tvDay;
    String tvTalk,tvNew;
    int ivImage;

    public SubData(int tvDay, String tvTalk, int ivImage,String tvNew) {
        this.tvDay = tvDay;
        this.tvTalk = tvTalk;
        this.ivImage = ivImage;
        this.tvNew = tvNew;
    }

    public SubData(int tvDay) {
        this.tvDay = tvDay;
    }

    public String getTvNew() {
        return tvNew;
    }

    public void setTvNew(String tvNew) {
        this.tvNew = tvNew;
    }

    public SubData(int tvDay, String tvTalk) {
        this.tvDay = tvDay;
        this.tvTalk = tvTalk;
    }
    public int getTvDay() {
        return tvDay;
    }

    public void setTvDay(int tvDay) {
        this.tvDay = tvDay;
    }

    public String getTvTalk() {
        return tvTalk;
    }

    public void setTvTalk(String tvTalk) {
        this.tvTalk = tvTalk;
    }

    public int getIvImage() {
        return ivImage;
    }

    public void setIvImage(int ivImage) {
        this.ivImage = ivImage;
    }
}
