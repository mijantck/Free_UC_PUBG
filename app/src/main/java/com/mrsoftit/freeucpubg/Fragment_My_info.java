package com.mrsoftit.freeucpubg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_My_info extends Fragment {


    public Fragment_My_info(){}

    private View offerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        offerView = inflater.inflate(R.layout.my_info, container, false);







        return offerView;
    }
}
