package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WedrdoActivity extends AppCompatActivity {

    String docId;


    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("order");
    CollectionReference notebookRefAdmin = FirebaseFirestore.getInstance()
            .collection("order");

    FirebaseFirestore firestore;
    public TextView ucTextView;
    double ucAmount;
    public String mainID;
    ProgressDialog progressDialog;


    private EditText nickName,gamerID;
    private ImageView ucButton;

    String ucAmu,uctaka;
    String id1;
    String nickName1;
    String transection1;
    TextView ucAmuTextView,ucTakaTextview,orederNow;
    EditText transactuionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedrdo);

        nickName = findViewById(R.id.weidroNickName);
        gamerID = findViewById(R.id.weidroId);

        ucButton = findViewById(R.id.weidroButton);

        final String customerUserName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        final String customerUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();


        ucButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(WedrdoActivity.this);
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner

                Date calendar = Calendar.getInstance().getTime();
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                String todayString = df.format(calendar);
                int datenew = Integer.parseInt(todayString);


                id1  = gamerID.getText().toString();
                nickName1 = nickName.getText().toString();



                if (id1.trim().isEmpty() || nickName1.trim().isEmpty() ) {
                    Toast.makeText(v.getContext(), "Please insert All Data", Toast.LENGTH_SHORT).show();

                    return;

                }


                if (ucAmount < 100.00){

                    Toast.makeText(WedrdoActivity.this, "low amount ", Toast.LENGTH_SHORT).show();

                }else {

                    progressDialog.show();
                    ucAmu = String.valueOf(ucAmount);



                    notebookRef.add(new OrderListModle( "Processing",customerUserName,customerUserEmail,ucAmu,"Free",id1,datenew,nickName1,"Withdraw Request",uid,null))
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                    if (task.isSuccessful()){

                                        docId = task.getResult().getId();

                                        Toast.makeText(v.getContext(), docId.toString(), Toast.LENGTH_SHORT).show();

                                        notebookRefAdmin.add(new OrderListModle( "Processing",customerUserName,customerUserEmail,ucAmu,"Free ",id1,datenew,nickName1,"Withdraw Request",uid,docId))
                                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                                        CollectionReference notebookRefuc = FirebaseFirestore.getInstance()
                                                                .collection("Users").document(uid).collection("ads");




                                                        notebookRefuc.document(mainID).update("ucAmount", 00.00).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {


                                                            }
                                                        });


                                                        progressDialog.dismiss();

                                                        finish();
                                                    }
                                                });

                                    }


                                }
                            });
                }


            }
        });








    }


    @Override
    public void onStart() {

        super.onStart();

        CollectionReference notebookRefuc = FirebaseFirestore.getInstance()
                .collection("Users").document(uid).collection("ads");
        ucTextView = findViewById(R.id.ucTolal);

        firestore = FirebaseFirestore.getInstance();

        notebookRefuc.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AdsUcModel adsUcModel = document.toObject(AdsUcModel.class);
                        ucTextView.setText(adsUcModel.getUcAmount()+"");
                        ucAmount = adsUcModel.getUcAmount();
                        mainID = adsUcModel.getUcid();

                    }
                } else {

                }
            }
        });

    }
}
