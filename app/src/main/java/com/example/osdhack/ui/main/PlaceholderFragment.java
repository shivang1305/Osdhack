package com.example.osdhack.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.osdhack.MainActivity;
import com.example.osdhack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    EditText pname,qty,price,proddescription;
    Button post;
    private FirebaseAuth mAuth;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab2_sell, container, true);
        final TextView textView = root.findViewById(R.id.section_label);
        mAuth= FirebaseAuth.getInstance();
        pname=root.findViewById(R.id.pname);
        qty=root.findViewById(R.id.qty);
        price=root.findViewById(R.id.price);
        proddescription=root.findViewById(R.id.pdescription);
        post=root.findViewById(R.id.post_btn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProdInfo();
            }
        });
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    public void saveProdInfo()
    {
        String pnm=pname.getText().toString();
        String q=qty.getText().toString();
        int qno=Integer.parseInt(q);
        String p=price.getText().toString();
        int pno=Integer.parseInt(p);
        String pd=proddescription.getText().toString();

        MainActivity.ProductInfo pi=new MainActivity.ProductInfo(pnm,qno,pno,pd);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Products");
        FirebaseUser user=mAuth.getCurrentUser();
        ref.child(user.getUid()).setValue(pi);

//        Toast.makeText(this, "Your Product details are posted ", Toast.LENGTH_SHORT).show();
    }
}