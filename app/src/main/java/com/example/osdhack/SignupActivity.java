package com.example.osdhack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailid,pass,name,pno;
    CheckBox cb;
    Button login,signup;
    String email,password,phno,nme;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.signup_email);
        pass=findViewById(R.id.signup_pass);
        name=findViewById(R.id.signup_name);
        pno=findViewById(R.id.signup_pno);
        signup=findViewById(R.id.signup_btn);
        login=findViewById(R.id.signup_loginbtn);
        cb=findViewById(R.id.signup_checkbox);
        pb=findViewById(R.id.signup_progressbar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent in =new Intent(SignupActivity.this,MainActivity.class);
                startActivity(in);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cb.isChecked())
                {
                    Toast.makeText(SignupActivity.this, "Please agree on Terms and Conditions", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser();
                }
            }
        });
    }

    public void registerUser() {

        email = emailid.getText().toString();
        password = pass.getText().toString();
        phno=pno.getText().toString();
        nme=name.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailid.setError("Enter valid email address");
            emailid.requestFocus();
            return;
        }
        else if (email.length() == 0) {
            emailid.setError("Enter email address");
            emailid.requestFocus();
            return;
        }
        else if(password.length()==0) {
            pass.setError("Enter password");
            pass.requestFocus();
            return;
        }
        else if(password.length()<6) {
            pass.setError("Password must characters long");
            pass.requestFocus();
            return;
        }
        else if(nme.length()==0)
        {
            name.setError("Enter name");
            name.requestFocus();
            return;
        }
        else if(phno.length()==0 || phno.length()<10)
        {
            pno.setError("Enter valid username");
            pno.requestFocus();
            return;
        }
        pb.setVisibility(View.VISIBLE);
        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "User is Registered Successfully", Toast.LENGTH_SHORT).show();
                                saveUserInfo();
                            }

                        }
                    });
        } catch (Exception e) {
            System.out.println("Error----->" + e.getMessage());
        }
    }

    public void saveUserInfo()
    {
        String nm=name.getText().toString();
        String e=emailid.getText().toString();
        String p=pno.getText().toString();

        UserInfo ui=new UserInfo(nm,e,p);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");
        FirebaseUser user=mAuth.getCurrentUser();
        ref.child(user.getUid()).setValue(ui);

        finish();
        Intent in =new Intent(SignupActivity.this,Main2Activity.class);
        startActivity(in);
    }
}
