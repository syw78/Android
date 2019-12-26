package com.example.user.viewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    //그냥 주는것 건들지 마라!
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //뷰페이저에서 프래그머느 교체를 보여주는 역할
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:  return Fragment1.newInstance();
            case 1:  return Fragment2.newInstance();
            case 2:  return Fragment3.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    //상단의 탭 레이아웃을 제목에 대한 텍스트를 선언한다.
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:  return "복숭아는 피치";
            case 1:  return "포도는 그레잎";
            case 2:  return "귤은 만다린";
            default: return null;
        }
    }
}
