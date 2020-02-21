package com.mrsoftit.freeucpubg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_offer extends Fragment {

public Fragment_offer(){}

    private View offerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        offerView = inflater.inflate(R.layout.offer_task, container, false);







        return offerView;
    }
}
