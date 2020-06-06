package com.shivam.feedfull_ngo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProfileRecyclerAdapter extends RecyclerView.Adapter<ProfileRecyclerAdapter.ProfileViewHolder> {

    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mname=new ArrayList<>();
    private ArrayList<String> mDescription=new ArrayList<>();
    private ArrayList<String> mitemId=new ArrayList<>();
    private Context mContext;

    public ProfileRecyclerAdapter(Context mContext,ArrayList<String> mImages,ArrayList<String> mname,ArrayList<String> mDescription,ArrayList<String>mitemId) {
        this.mContext = mContext;
        this.mImages=mImages;
        this.mname=mname;
        this.mDescription=mDescription;
        this.mitemId=mitemId;

    }



    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recycler,parent,false);
        ProfileRecyclerAdapter.ProfileViewHolder holder=new ProfileRecyclerAdapter.ProfileViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {

        try {
            Glide.with(mContext).load(mImages.get(position)).into(holder.profiletargetImage);
            //holder.profiletargetImage.setImageBitmap(mImages.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            holder.profiletargetText.setText(mname.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            holder.desctarget.setText(mDescription.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            holder.profileparentContext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ON CLICKING THE TAB

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.profileparentContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),itemaccept.class);
                intent.putExtra("title",mname.get(position));
                intent.putExtra("descrip",mDescription.get(position));
                intent.putExtra("image",mImages.get(position));
                intent.putExtra("id",mitemId.get(position));
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mname.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{

        TextView profiletargetText;
        ImageView profiletargetImage;
        ConstraintLayout profileparentContext;
        TextView desctarget;


        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profiletargetImage=itemView.findViewById(R.id.profileimageOfTargets);
            profiletargetText=itemView.findViewById(R.id.profiletargetTextView);
            profileparentContext=itemView.findViewById(R.id.profileparent_layout);
            desctarget=itemView.findViewById(R.id.descptargetText);

        }
    }








}
