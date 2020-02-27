package com.mrsoftit.freeucpubg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Fragment_Uc_buy extends Fragment {


  public Fragment_Uc_buy(){

  }

  private View offerView;
  private RecyclerView recyclerView;
  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private CollectionReference notebookRef = db.collection("buy");




  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    offerView = inflater.inflate(R.layout.uc_buy, container, false);

    recyclerView = offerView.findViewById(R.id.recyclearView);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(offerView.getContext(),2,GridLayoutManager.VERTICAL,false);

    recyclerView.setLayoutManager(gridLayoutManager );





    return offerView;
  }


  @Override
  public void onStart() {

    super.onStart();



    FirestoreRecyclerOptions options =
            new FirestoreRecyclerOptions.Builder<BuyUcModle>()
                    .setQuery(notebookRef, BuyUcModle.class)
                    .build();

    FirestoreRecyclerAdapter<BuyUcModle, RoutinViewHolder> adapter
            = new FirestoreRecyclerAdapter<BuyUcModle, RoutinViewHolder>(options) {
      @Override
      protected void onBindViewHolder(@NonNull final RoutinViewHolder holder, final int position, @NonNull final BuyUcModle model) {
        holder.ucAmount.setText(model.getUcAmount());
        holder.ucTaka.setText(model.getUcTaka());

        holder.ucTaka.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            String ucAmount = model.getUcAmount();
            String ucTaka = model.getUcTaka();

            Intent uclist = new Intent(getContext(), OrderActivity.class);
            uclist.putExtra("ucAmu",ucAmount);
            uclist.putExtra("ucTaka",ucTaka);

            startActivity(uclist);

          }
        });


      }

      @NonNull
      @Override
      public RoutinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.buy_uc_single_item, viewGroup, false);

        RoutinViewHolder viewHolder = new RoutinViewHolder(view);


        return viewHolder;
      }
    };

    recyclerView.setAdapter(adapter);
    adapter.startListening();

  }

  public static class RoutinViewHolder extends RecyclerView.ViewHolder {

    TextView ucAmount,ucTaka ;



    public RoutinViewHolder(@NonNull View itemView) {
      super(itemView);
      ucAmount = itemView.findViewById(R.id.ucAmuont);
      ucTaka = itemView.findViewById(R.id.ucTaka);



    }
  }}