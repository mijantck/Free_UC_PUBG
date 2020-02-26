package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    String ucAmu,uctaka;
    String id1;
    String nickName1;
    String transection1;
    TextView ucAmuTextView,ucTakaTextview,orederNow,Number;
    EditText id,nickName,transactuionId;

    String docId;


    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("order");
    CollectionReference notebookRefAdmin = FirebaseFirestore.getInstance()
            .collection("order");

    private RadioGroup radioSexGroup;
    private RadioButton bKash,rokect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Bundle bundle = getIntent().getExtras();
               ucAmu=  bundle.getString("ucAmu");
               uctaka=  bundle.getString("ucTaka");



                 radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
                 bKash = findViewById(R.id.radiobkash);
                 Number = findViewById(R.id.Number);
                 bKash.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Number.setText("01733-883310");
                     }
                 });
                 rokect = findViewById(R.id.radioRocekt);
                 rokect.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         Number.setText("01733-8833105");
                     }
                 });


               final String customerUserName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
               final String customerUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

               ucAmuTextView = findViewById(R.id.ucAmu);
               ucTakaTextview = findViewById(R.id.uctaka);
               id = findViewById(R.id.id);
               nickName = findViewById(R.id.nickName);
               transactuionId = findViewById(R.id.transection);
               orederNow = findViewById(R.id.orderNow);

               ucAmuTextView.setText(ucAmu);
               ucTakaTextview.setText(uctaka);

               orederNow.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(final View v) {

                       progressDialog = new ProgressDialog(OrderActivity.this);
                       progressDialog.setMessage("Loading..."); // Setting Message
                       progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner


                       Date calendar = Calendar.getInstance().getTime();
                       DateFormat df = new SimpleDateFormat("yyyyMMdd");
                       String todayString = df.format(calendar);
                       int datenew = Integer.parseInt(todayString);



                       id1  = id.getText().toString();
                       nickName1 = nickName.getText().toString();
                       transection1  = transactuionId.getText().toString();



                       if (id1.trim().isEmpty() || nickName1.trim().isEmpty() || transection1.trim().isEmpty() ) {
                           Toast.makeText(v.getContext(), "Please insert All Data", Toast.LENGTH_SHORT).show();

                           return;

                       }




                       progressDialog.show();

                       notebookRef.add(new OrderListModle( "Processing",customerUserName,customerUserEmail,ucAmu,uctaka,id1,datenew,nickName1,transection1,uid,null))
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {

                                if (task.isSuccessful()){

                                    docId = task.getResult().getId();

                                    Toast.makeText(v.getContext(), docId.toString(), Toast.LENGTH_SHORT).show();

                                    notebookRefAdmin.add(new OrderListModle( "Processing",customerUserName,customerUserEmail,ucAmu,uctaka,id1,datenew,nickName1,transection1,uid,docId))
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                                    Toast.makeText(v.getContext(), docId.toString() +"admin ", Toast.LENGTH_SHORT).show();

                                                    progressDialog.dismiss();
                                                    finish();
                                                }
                                            });

                                }


                            }
                        });






                   }
               });






    }
}
