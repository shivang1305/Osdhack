package com.example.osdhack;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SellActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText pname,qty,price,proddescription;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);


        mAuth= FirebaseAuth.getInstance();
        pname=findViewById(R.id.pname);
        qty=findViewById(R.id.qty);
        price=findViewById(R.id.price);
        proddescription=findViewById(R.id.pdescription);
        post=findViewById(R.id.post_btn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProdInfo();
            }
        });
    }

    public void saveProdInfo()
    {
        String pnm=pname.getText().toString();
        String q=qty.getText().toString();
        int qno=Integer.parseInt(q);
        String p=price.getText().toString();
        int pno=Integer.parseInt(p);
        String pd=proddescription.getText().toString();

        ProductInfo pi=new ProductInfo(pnm,qno,pno,pd);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Products");
        FirebaseUser user=mAuth.getCurrentUser();
        ref.child(user.getUid()).setValue(pi);

        Toast.makeText(this, "Your Product details are posted ", Toast.LENGTH_SHORT).show();
    }
}
