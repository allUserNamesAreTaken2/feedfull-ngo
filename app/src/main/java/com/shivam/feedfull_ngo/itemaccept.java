package com.shivam.feedfull_ngo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class itemaccept extends AppCompatActivity {
    TextView title;
    TextView descrip;
    ImageView image;
    Button accept;
    String uid;
    String itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemaccept);
        title=findViewById(R.id.textView4);
        descrip=findViewById(R.id.textView5);
        image=findViewById(R.id.imageView2);
        accept=findViewById(R.id.button);
        Bundle extras = getIntent().getExtras();
        uid= FirebaseAuth.getInstance().getUid();

        if (extras != null) {
          title.setText(extras.getString("title"));
          descrip.setText(extras.getString("descrip"));
           Glide.with(getApplicationContext()).load(extras.getString("image")).into(image);
           itemid=extras.getString("id");
        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseChanges();
                Toast.makeText(getApplicationContext(),"Donation Accepted",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void databaseChanges(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        reference.child("items").child(itemid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                changevalue((Map<String,String>)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("donated").child(itemid).removeValue();
    }

    public void changevalue(Map<String,String> info){
        info.put("ngo",uid);
        FirebaseDatabase.getInstance().getReference().child("donated").push().setValue(info);
        FirebaseDatabase.getInstance().getReference().child("items").child(itemid).removeValue();
    }

}