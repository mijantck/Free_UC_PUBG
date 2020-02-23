package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class RewordActivity extends AppCompatActivity {



    private Button adsreword;
    private TextView havecount,haveuc;

    ProgressDialog progressDialog;


    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("ads");

    FirebaseFirestore firestore;

    public double uc = 000.00;
    public int count = 5;
    public int date ;
    public boolean entry = false ;
    public String ucid;


    @Override
    public void onStart() {

        super.onStart();


        Date calendar1 = Calendar.getInstance().getTime();
        DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        String todayString = df1.format(calendar1);
        final int datenew = Integer.parseInt(todayString);


        firestore = FirebaseFirestore.getInstance();
        notebookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    AdsUcModel adsUcModel = documentSnapshot.toObject(AdsUcModel.class);
                   uc = adsUcModel.getUcAmount();
                   count = adsUcModel.getCount();
                   date = adsUcModel.getDate();
                   entry = adsUcModel.isFirstTime();
                   ucid = adsUcModel.getUcid();
                   havecount.setText(count+"");
                    haveuc.setText(uc+"");

                   if ((datenew != date ) && count == 0 ){

                       notebookRef.document(ucid)
                               .update("count",5).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                           }
                       });
                   }
                }
            }
        });

    }



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reword);

            adsreword = findViewById(R.id.adsreword);
            havecount = findViewById(R.id.havecount);
            haveuc = findViewById(R.id.haveuc);

            progressDialog = new ProgressDialog(RewordActivity.this);
            progressDialog.setMessage("Loading..."); // Setting Message
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);



            adsreword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    progressDialog.show();

                    Date calendar = Calendar.getInstance().getTime();

                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    String todayString = df.format(calendar);
                    int datenew = Integer.parseInt(todayString);





                    if (entry == false && count >= 0 && count <= 6  ){

                        count --;
                        uc = uc +.25;

                        havecount.setText(count+"");
                        haveuc.setText(uc+"");

                        notebookRef.add(new AdsUcModel(uc,count,datenew,true,null))
                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                        if (task.isSuccessful()){

                                            ucid  = task.getResult().getId();

                                            notebookRef.document(ucid)
                                                    .update("ucAmount",uc,"ucid",ucid).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(RewordActivity.this, "fiarst ", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                }
                                            });
                                        }
                                    }
                                });


                        Toast.makeText(RewordActivity.this, count+"", Toast.LENGTH_SHORT).show();
                    } else if (count >= 1 && count <= 6 ){

                            count --;
                        uc = uc +.25;

                            havecount.setText(count+"");

                             if(ucid != null){
                        notebookRef.document(ucid)
                                .update("ucAmount",uc,"count",count).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RewordActivity.this, "second ", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });}

                            Toast.makeText(RewordActivity.this, count+"", Toast.LENGTH_SHORT).show();
                        }
                        if (count == 0 ){

                            havecount.setText(count+"");
                            havecount.setText("nxt Day try "+count);

                            if(ucid != null){

                            notebookRef.document(ucid)
                                    .update("ucAmount",uc,"count",count,"date",datenew)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RewordActivity.this, "dismis ", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });}

                        }
                    }


            });
    }


}
