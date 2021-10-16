package com.example.reservasdeportivas.fragment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.reservasdeportivas.fragment.Fragment_banner_admin_dos;
import com.example.reservasdeportivas.fragment.Fragment_banner_admin_one;
import com.example.reservasdeportivas.fragment.Fragment_banner_admin_tres;

public class AdapterBannner extends FragmentStatePagerAdapter {

    public AdapterBannner(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment_banner_admin_one tab1 = new Fragment_banner_admin_one();
                return tab1;
            case 1:
                Fragment_banner_admin_dos tab2 = new Fragment_banner_admin_dos();
                return tab2;
            case 2:
                Fragment_banner_admin_tres tab3 = new Fragment_banner_admin_tres();
                return tab3;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}