package com.example.osdhack;

import android.content.Intent;
import android.os.Bundle;

import com.example.osdhack.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osdhack.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

   private FirebaseAuth mAuth;
//    EditText pname,qty,price,proddescription;
//    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
//        mAuth= FirebaseAuth.getInstance();
//        pname=findViewById(R.id.pname);
//        qty=findViewById(R.id.qty);
//        price=findViewById(R.id.price);
//        proddescription=findViewById(R.id.pdescription);
//        post=findViewById(R.id.post_btn);
//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveProdInfo();
//            }
//        });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Logging out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }

//    public void saveProdInfo()
//    {
//        String pnm=pname.getText().toString();
//        String q=qty.getText().toString();
//        int qno=Integer.parseInt(q);
//        String p=price.getText().toString();
//        int pno=Integer.parseInt(p);
//        String pd=proddescription.getText().toString();
//
//        ProductInfo pi=new ProductInfo(pnm,qno,pno,pd);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("Products");
//        FirebaseUser user=mAuth.getCurrentUser();
//        ref.child(user.getUid()).setValue(pi);
//
//        Toast.makeText(this, "Your Product details are posted ", Toast.LENGTH_SHORT).show();
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.contextmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.chat_logout)
        {
            finish();
//            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else if(item.getItemId()==R.id.chat_exit)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ProductInfo {
        public String pname;
        public int qty;
        public int price;
        public String productdescription;

        public ProductInfo(String pname,int qty,int price,String productdescription)
        {
            this.pname=pname;
            this.qty=qty;
            this.price=price;
            this.productdescription=productdescription;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getProductdescription() {
            return productdescription;
        }

        public void setProductdescription(String productdescription) {
            this.productdescription = productdescription;
        }
    }
}