package com.mrsoftit.freeucpubg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    String ucAmu,uctaka;

    TextView ucAmuTextView,ucTakaTextview,orederNow;
    EditText id,nickName,transactuionId;

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("order");
    CollectionReference notebookRefAdmin = FirebaseFirestore.getInstance()
            .collection("order");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        Bundle bundle = getIntent().getExtras();
               ucAmu=  bundle.getString("ucAmu");
               uctaka=  bundle.getString("ucTaka");

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
                   public void onClick(View v) {


                       String id1 = id.getText().toString();
                       String nickName1 = nickName.getText().toString();
                       String transection1 = transactuionId.getText().toString();

                       if (id1.trim().isEmpty() || nickName1.trim().isEmpty() || transection1.trim().isEmpty() ) {
                           Toast.makeText(v.getContext(), "Please insert All Data", Toast.LENGTH_SHORT).show();

                           return;

                       }



                       notebookRef.add(new OrderListModle( customerUserName,customerUserEmail,ucAmu,uctaka,id1,nickName1,transection1));

                       notebookRefAdmin.add(new OrderListModle( customerUserName,customerUserEmail,ucAmu,uctaka,id1,nickName1,transection1));

                       Toast.makeText(v.getContext(), "Order Complete", Toast.LENGTH_SHORT).show();


                       finish();



                   }
               });






    }
}
