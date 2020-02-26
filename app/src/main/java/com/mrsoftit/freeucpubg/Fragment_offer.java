package com.mrsoftit.freeucpubg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Fragment_offer extends Fragment {

public Fragment_offer(){}

    private View offerView;
    private ImageView freeUc,spinBtnUc,dealyBunas;
    public TextView ucview;

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("ads");

    FirebaseFirestore firestore;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        offerView = inflater.inflate(R.layout.offer_task, container, false);

       freeUc = offerView.findViewById(R.id.AdsReword);
       spinBtnUc = offerView.findViewById(R.id.spinBtnUc);
        dealyBunas = offerView.findViewById(R.id.dealyBunas);


        spinBtnUc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(offerView.getContext(),WheelActivity.class);

                startActivity(intent);

            }
        });



       freeUc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(offerView.getContext(),RewordActivity.class);

               startActivity(intent);

           }
       });

        dealyBunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(offerView.getContext(),DailyActivity.class);

                startActivity(intent);

            }
        });


        return offerView;
    }

}
