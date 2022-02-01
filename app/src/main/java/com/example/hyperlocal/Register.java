package com.example.hyperlocal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    EditText mfullName, mphoneNo, memailId, mpassword;
    Button mregisterBtn;
    TextView mloginBtn;
    FirebaseAuth fauth;
    ProgressBar pbar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mfullName = findViewById(R.id.fullName);
        mphoneNo = findViewById(R.id.phoneNo);
        memailId = findViewById(R.id.emailId);
        mpassword = findViewById(R.id.password);
        mregisterBtn = findViewById(R.id.registerBtn);
        mloginBtn = findViewById(R.id.createText);
        fauth = FirebaseAuth.getInstance();
        pbar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();


        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = memailId.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                final String fullName = mfullName.getText().toString();
                final String phone    = mphoneNo.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    memailId.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is required.");
                    return;

                }

                if (password.length() < 6) {
                    mpassword.setError("Password must be greater than 6 characters");
                    return;
                }

                pbar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser fuser = fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "On Failure,email not sent" + e.getMessage());
                                }
                            });
                            Toast.makeText(
                                    Register.this, "User created", Toast.LENGTH_SHORT).show();
                            userID = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        } else {
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));

            }


        });




    }
}