package com.shivam.feedfull_ngo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class NEEDS extends Fragment {
    FloatingActionButton addButton;

    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mname=new ArrayList<>();
    private ArrayList<String> mDescription=new ArrayList<>();
    private ArrayList<String> mitemId=new ArrayList<>();

    RecyclerView recyclerView;
    ProgressBar progressBar;
    DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intitngoArrays();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_n_e_e_d_s, container, false);
        addButton=v.findViewById(R.id.floatingActionButton);
        recyclerView=v.findViewById(R.id.recyclerViewngo);
        progressBar=v.findViewById(R.id.progressBar4);



        addButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), addrequirements.class);
                startActivity(intent);
            }
        });


        return v;
    }


    public void intitngoArrays(){

        try {
            mDatabase= FirebaseDatabase.getInstance().getReference();

            mDatabase.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot != null) {

                        collectusers((Map<String, Object>) dataSnapshot.getValue());
                    }


                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void collectusers(Map<String,Object> users) {
        try {

            //iterate through each user, ignoring their UID
            for (Map.Entry<String, Object> entry : users.entrySet()) {
                mitemId.add((entry.getKey()));
                Map singleUser = (Map) entry.getValue();
                mname.add((singleUser.get("title").toString()));
                mDescription.add(singleUser.get("description").toString());


                try {

                    mImages.add(singleUser.get("imageUrl").toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
///initiate adapter
            ProfileRecyclerAdapter profileRecyclerAdapter = new ProfileRecyclerAdapter(getContext(), mImages, mname, mDescription, mitemId);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(profileRecyclerAdapter);

            progressBar.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
        }


    }


}