package com.example.osdhack;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {
    LinearLayout ll;
    DatabaseReference myRef;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ll = findViewById(R.id.ll);
        pb = findViewById(R.id.pb);
        myRef = FirebaseDatabase.getInstance().getReference().child("Products");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ll.removeAllViews();
                pb.setVisibility(View.INVISIBLE);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    TextView tv = new TextView(BuyActivity.this);
                    tv.setText(child.getValue().toString());
                    tv.setTextSize(18);
                    ll.addView(tv);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }
}
