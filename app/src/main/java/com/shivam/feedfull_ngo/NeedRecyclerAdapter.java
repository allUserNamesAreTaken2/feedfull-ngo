package com.shivam.feedfull_ngo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NeedRecyclerAdapter extends RecyclerView.Adapter<NeedRecyclerAdapter.NeedViewHolder>{

    Context mcontext;
    ArrayList<String> mitemneed=new ArrayList<>();
    ArrayList<String>mitemneedesc=new ArrayList<>();


    public NeedRecyclerAdapter(Context mcontext,  ArrayList<String> mitemneed, ArrayList<String>mitemneedesc){
        this.mcontext = mcontext;
        this.mitemneed=mitemneed;
        this.mitemneedesc=mitemneedesc;
    }

    @NonNull
    @Override
    public NeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.needrecycler,parent,false);
        NeedViewHolder holder=new NeedViewHolder(view);
        Log.i("hadd haii","hdhdhdh");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NeedViewHolder holder, final int position) {
        holder.descngo.setText(mitemneedesc.get(position));
        holder.itemneed.setText(mitemneed.get(position));

    }

    @Override
    public int getItemCount() {
        return mitemneed.size();
    }


    public class NeedViewHolder extends RecyclerView.ViewHolder{

        TextView itemneed;
        TextView descngo;
        ConstraintLayout layout;
        ConstraintLayout constraintLayout;

        public NeedViewHolder(@NonNull View itemView) {
            super(itemView);
            itemneed=itemView.findViewById(R.id.itemngotextView10);
            descngo=itemView.findViewById(R.id.ngodesceditText2);
            layout=itemView.findViewById(R.id.needsrecyclerr);
            constraintLayout=itemView.findViewById(R.id.innerconst);
        }
    }



}
