package com.shivam.feedfull_ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.TreeMap;

public class addrequirements extends AppCompatActivity {
    EditText ngoname;
    EditText ngoitemnaeme;
    EditText itemdescneed;
    Button submitneed;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrequirements);
        ngoname=findViewById(R.id.ngonameneed);
        ngoitemnaeme=findViewById(R.id.itemneed);
        itemdescneed=findViewById(R.id.itemdescneed);
        submitneed=findViewById(R.id.additemneed );
        final DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
       uid=FirebaseAuth.getInstance().getCurrentUser().getUid();



        submitneed.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ngoitemnaeme.getText().toString().length()>2&&itemdescneed.getText().toString().length()>20&&ngoname.getText().toString().length()>2) {
                    Map<String,String> needs=new TreeMap<String, String>();
                    needs.put("itemname",ngoitemnaeme.getText().toString());
                    needs.put("itemdesc",itemdescneed.getText().toString());
                    needs.put("ngoname",ngoname.getText().toString());
                    needs.put("ngoid",uid);

                    mdatabase.child("needs").push().setValue(needs);

                    Toast.makeText(getApplicationContext(),"ADDED",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),NEEDS.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"PLEASE FILL ALL FIELDS",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
