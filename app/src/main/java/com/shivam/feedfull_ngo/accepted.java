package com.shivam.feedfull_ngo;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class accepted extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mname=new ArrayList<>();
    private ArrayList<String> mDescription=new ArrayList<>();
    private ArrayList<String> mitemId=new ArrayList<>();
    DatabaseReference mDatabase;
    String uid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       intitngoArrays();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_accepted, container, false);
       recyclerView=v.findViewById(R.id.recyclerView);
       progressBar=v.findViewById(R.id.progressBar);
       uid= FirebaseAuth.getInstance().getUid();

       return v;
    }
    public void intitngoArrays(){

        try {
            mDatabase= FirebaseDatabase.getInstance().getReference();

            mDatabase.child("donated").addListenerForSingleValueEvent(new ValueEventListener() {
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

            for (Map.Entry<String, Object> entry : users.entrySet()) {

                //Get user map
                Map singleUser = (Map) entry.getValue();
                //Get phone field and append to list


                if (singleUser.get("ngo").toString().equals(uid)) {
                    mname.add((singleUser.get("title").toString()));
                    mDescription.add(singleUser.get("description").toString());


                    try {

                        mImages.add(singleUser.get("imageUrl").toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


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