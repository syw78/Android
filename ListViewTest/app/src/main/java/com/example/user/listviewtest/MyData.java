package com.example.user.listviewtest;

public class MyData {
    private int ivImage1,ivImage2;
    private String textView1,textView2;

    public MyData(int ivImage1, int ivImage2, String textView1, String textView2) {
        this.ivImage1 = ivImage1;
        this.ivImage2 = ivImage2;
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    public int getIvImage1() {
        return ivImage1;
    }

    public void setIvImage1(int ivImage1) {
        this.ivImage1 = ivImage1;
    }

    public int getIvImage2() {
        return ivImage2;
    }

    public void setIvImage2(int ivImage2) {
        this.ivImage2 = ivImage2;
    }

    public String getTextView1() {
        return textView1;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public void setTextView2(String textView2) {
        this.textView2 = textView2;
    }
}
