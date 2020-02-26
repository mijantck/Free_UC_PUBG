package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyActivity extends AppCompatActivity {

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("ads");
    CollectionReference notebookDaily = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("Daily");


    FirebaseFirestore firestore;
    public ImageView dealyBunas;
    public TextView ucTextView,havecount;
    public int datedaily;
    public int count;
    public double uc = 000.00;
    public String ucid;
    public  String mainId;
    public String dailyId;
    public  boolean firstTime = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        havecount = findViewById(R.id.havecount);
        dealyBunas = findViewById(R.id.dealyBunas);
        havecount.setText(count+"");

        dealyBunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ( count == 1  && dailyId != null ) {

                    ucTextView = findViewById(R.id.ucTolal);

                    count--;
                    uc += .75;

                    ucTextView.setText(uc+"");
                    havecount.setText(count+"");





                    Date calendar1 = Calendar.getInstance().getTime();
                    DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
                    String todayString = df1.format(calendar1);
                    final int datenew = Integer.parseInt(todayString);

                    notebookDaily.document(dailyId).update("count", -1, "date", datenew)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    notebookRef.document(mainId)
                                            .update("ucAmount", uc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                        }
                                    });


                                }
                            });

                }else if(firstTime == false){

                    uc += .75;
                    count--;

                    ucTextView.setText(uc+"");
                    havecount.setText(count+"");

                    Date calendar1 = Calendar.getInstance().getTime();
                    DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
                    String todayString = df1.format(calendar1);
                    final int datenew = Integer.parseInt(todayString);


                    notebookDaily.add(new DailyBuonas(-1, datenew, null,true))
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                    if (task.isSuccessful()) {

                                        dailyId = task.getResult().getId();

                                        notebookDaily.document(dailyId).update("count", -1, "date", datenew, "id", dailyId,"firstTime",true)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {


                                                    }
                                                });
                                    }
                                }
                            });
                }else {
                    Toast.makeText(DailyActivity.this, " You have no change ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    @Override
    public void onStart() {

        super.onStart();

        ucTextView = findViewById(R.id.ucTolal);

        firestore = FirebaseFirestore.getInstance();


        Date calendar1 = Calendar.getInstance().getTime();
        DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        String todayString = df1.format(calendar1);
        final int datenew = Integer.parseInt(todayString);


        firestore = FirebaseFirestore.getInstance();

        notebookRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AdsUcModel adsUcModel = document.toObject(AdsUcModel.class);
                        ucTextView.setText(adsUcModel.getUcAmount()+"");
                        mainId =adsUcModel.getUcid();
                        uc = adsUcModel.getUcAmount();

                    }

                    notebookDaily.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    DailyBuonas dailyBuonas = document.toObject(DailyBuonas.class);

                                    havecount.setText(dailyBuonas.getCount()+"");
                                    count = dailyBuonas.getCount();
                                    datedaily =dailyBuonas.getDate();
                                    firstTime = dailyBuonas.isFirstTime();
                                    dailyId = dailyBuonas.getId();

                                    int  count1 = dailyBuonas.getCount();

                                    if (count1 == -1){
                                        havecount.setText("00");
                                    }

                                    if (datedaily != datenew   ){


                                        if ( count1 == -1)

                                            havecount.setText(count+"");

                                        notebookDaily.document(dailyId).update("count", 1)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                    }

                                }
                            }
                        }
                    });




                }
            }
        });



    }
}
