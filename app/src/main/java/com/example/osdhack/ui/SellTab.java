package com.example.osdhack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.example.osdhack.R;
import com.google.firebase.auth.FirebaseAuth;


public class SellTab extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.tab2_sell,container,false);
        return rootView;
    }


}
