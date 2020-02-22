package com.mrsoftit.freeucpubg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Fragment_My_info extends Fragment {


    public Fragment_My_info() {
    }

    private View offerView;

    private Button ucOrderListButton, WeddroUcButton;
    RecyclerView recyclerView;

    String uid = FirebaseAuth.getInstance().getUid();
    CollectionReference notebookRef = FirebaseFirestore.getInstance()
            .collection("Users").document(uid).collection("order");
    CollectionReference notebookRefAdmin = FirebaseFirestore.getInstance()
            .collection("order");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        offerView = inflater.inflate(R.layout.my_info, container, false);


        WeddroUcButton = offerView.findViewById(R.id.WeddroUcButton);

        ucOrderListButton = offerView.findViewById(R.id.ucOrderListButton);

        recyclerView = offerView.findViewById(R.id.ucOrederlist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(offerView.getContext(), 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);


        ucOrderListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean open = true;
                if (open == true){

                    recyclerView.setVisibility(View.VISIBLE);
                    open = false;
                }else if (open == false){

                    recyclerView.setVisibility(View.GONE);
                    open = true;
                }

            }
        });


        return offerView;
    }


    @Override
    public void onStart() {

        super.onStart();



        FirestoreRecyclerOptions options =
                new FirestoreRecyclerOptions.Builder<OrderListModle>()
                        .setQuery(notebookRef, OrderListModle.class)
                        .build();

        FirestoreRecyclerAdapter<OrderListModle,ViewHolder> adapter
                = new FirestoreRecyclerAdapter<OrderListModle, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder holder, final int position, @NonNull final OrderListModle model) {

                holder.status.setText(model.getStatus());
                holder.email.setText(model.getCustomarEmgail());
                holder.ucamount.setText(model.getUcAmu());
                holder.ucprice.setText(model.getUcTaka());
                holder.gamerid.setText(model.getId());
                holder.nicname.setText(model.getNickName());
                holder.transactionid.setText(model.getBkasTransectionId());


            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.orderlist, viewGroup, false);

                ViewHolder viewHolder = new ViewHolder(view);


                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView status,email,ucamount,ucprice,gamerid ,nicname,transactionid;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            email = itemView.findViewById(R.id.email);
            ucamount = itemView.findViewById(R.id.ucamuont);
            ucprice = itemView.findViewById(R.id.ucprice);
            gamerid = itemView.findViewById(R.id.gramerid);
            nicname = itemView.findViewById(R.id.gamerNikck);
            transactionid = itemView.findViewById(R.id.transectionid);



        }



    }


}
