package com.mrsoftit.freeucpubg;

import android.content.Intent;
import android.media.Image;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class HistoryAdapter extends FirestoreRecyclerAdapter<OrderListModle,HistoryAdapter.NotViewHolde> {

    public HistoryAdapter(@NonNull FirestoreRecyclerOptions<OrderListModle> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull final NotViewHolde holder, int position, @NonNull OrderListModle model) {

        holder.status.setText(model.getStatus());
        holder.dateOr.setText(model.getDate()+"");
        holder.email.setText(model.getCustomarEmgail());
        holder.ucamount.setText(model.getUcAmu());
        holder.ucprice.setText(model.getUcTaka());
        holder.gamerid.setText(model.getId());
        holder.nicname.setText(model.getNickName());
        holder.transactionid.setText(model.getBkasTransectionId());


    }

    @NonNull
    @Override
    public NotViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist,
                parent, false);
        return new NotViewHolde(v);
    }

    class NotViewHolde extends RecyclerView.ViewHolder{

        TextView status,email,ucamount,ucprice,gamerid ,nicname,transactionid,dateOr;




        public NotViewHolde(@NonNull final View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.status);
            dateOr = itemView.findViewById(R.id.dateOr);
            email = itemView.findViewById(R.id.email);
            ucamount = itemView.findViewById(R.id.ucamuont);
            ucprice = itemView.findViewById(R.id.ucprice);
            gamerid = itemView.findViewById(R.id.gramerid);
            nicname = itemView.findViewById(R.id.gamerNikck);
            transactionid = itemView.findViewById(R.id.transectionid);
        }
    }

}
