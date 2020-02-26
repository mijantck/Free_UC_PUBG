package com.mrsoftit.freeucpubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class OrderHIstoryActivity extends AppCompatActivity {

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebooUC = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("ads");

    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("order");
    CollectionReference notebookRefAdmin = FirebaseFirestore.getInstance()
            .collection("order");

    FirebaseFirestore firestore;
    public TextView ucTextView;

    private RecyclerView recyclerView;
    private  HistoryAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerView = findViewById(R.id.recyclearVieworderhistory);

        recyclear();

    }


    private void recyclear() {

        Query query = notebookRef.orderBy("date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<OrderListModle> options = new FirestoreRecyclerOptions.Builder<OrderListModle>()
                .setQuery(query, OrderListModle.class)
                .build();

        adapter = new HistoryAdapter(options);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1, GridLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.recyclearVieworderhistory);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(gridLayoutManager);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(adapter);


    }
    @Override
    public void onStart() {

        super.onStart();

        adapter.startListening();

        ucTextView = findViewById(R.id.ucTolal);

        firestore = FirebaseFirestore.getInstance();

        notebooUC.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AdsUcModel adsUcModel = document.toObject(AdsUcModel.class);
                        ucTextView.setText(adsUcModel.getUcAmount()+"");
                    }
                } else {

                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
