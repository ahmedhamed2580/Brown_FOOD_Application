package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivitysignin3 extends AppCompatActivity {
TextView btnsignins;
Button signupt;
EditText editemail,editpassword,repassword,editname,editadress,editphone;
TextInputLayout emilo ,passworde ,repassworde, aderesseo, phoneo, nameo;
DBHELPER db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsignin3);
        signupt=findViewById(R.id.signups);
        editemail=findViewById(R.id.email);
        editpassword=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        editname=findViewById(R.id.nameedittext);
        editadress=findViewById(R.id.adressedittext);
        editphone=findViewById(R.id.phoneedittext3);
        nameo=findViewById(R.id.nameedittexte);
        emilo=findViewById(R.id.emaildittexte);
        passworde=findViewById(R.id.passwordedittexte);
        repassworde=findViewById(R.id.repasswordedittexte);
        aderesseo=findViewById(R.id.adressedittexte);
        phoneo=findViewById(R.id.phoneedittexte3);
btnsignins=findViewById(R.id.btnsignin);
        db=new DBHELPER(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        signupt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editemail.getText().toString();
                String password=editpassword.getText().toString();
                String name=editname.getText().toString();
                String adress=editadress.getText().toString();
                String phone=editphone.getText().toString();
                String repasswords=repassword.getText().toString();
                if(TextUtils.isEmpty(password)||TextUtils.isEmpty(repasswords)
                        ||TextUtils.isEmpty(name) ||TextUtils.isEmpty(adress) ||TextUtils.isEmpty(phone)){
                    if(TextUtils.isEmpty(email)) {
                        emilo.setErrorEnabled(true);
                        emilo.setError("Filed Requierd");
                        emilo.requestFocus();
                    }
                    if(TextUtils.isEmpty(name)) {
                        nameo.requestFocus();
                        nameo.setErrorEnabled(true);
                        nameo.setError("Filed Requierd");
                    }
                    if(TextUtils.isEmpty(password)) {
                        passworde.setErrorEnabled(true);
                        passworde.setError("Filed Requierd");
                        passworde.requestFocus();
                    }
                    if(TextUtils.isEmpty(repasswords)) {
                        repassworde.setErrorEnabled(true);
                        repassworde.setError("Filed Requierd");
                        repassworde.requestFocus();
                    }
                    if(TextUtils.isEmpty(adress)) {
                        aderesseo.setErrorEnabled(true);
                        aderesseo.setError("Filed Requierd");
                        aderesseo.requestFocus();
                    }

                    phoneo.setErrorEnabled(true);
                    phoneo.setError("Filed Requierd");
                    phoneo.requestFocus();

                    editemail.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            emilo.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    editname.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            nameo.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    editpassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            passworde.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }

                    });
                    repassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            repassworde.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    editadress.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            aderesseo.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    }

                    );
                    editphone.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            phoneo.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }


                    });


                }
                else if (isValidEmail(email)!=true) {
                    emilo.setErrorEnabled(true);
                    emilo.setError("Error Email");
                    editemail.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            emilo.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    }

                    );
                    emilo.requestFocus();



                } else if (isValidphone(phone)!=true) {
                    Toast.makeText(MainActivitysignin3.this, "Error PhoneNumber", Toast.LENGTH_SHORT).show();
                    phoneo.setErrorEnabled(true);
                    phoneo.setError("Error PhoneNumber");
                    phoneo.requestFocus();
                    editphone.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            phoneo.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    }
                    );


                } else{
                    if(password.equals(repasswords)){
                        Boolean checkemail=db.check(email);
                        if(checkemail==false){
                            Boolean insert=db.insert(email,password,name,adress,phone,null);
                            if(insert==true) {
                                Toast.makeText(MainActivitysignin3.this, "Registerd Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivitysignin3.this, MainActivitysignin2.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivitysignin3.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(MainActivitysignin3.this, "User Already exists", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else {
                        passworde.setErrorEnabled(true);
                        passworde.setError("Password Not Match");
                        passworde.requestFocus();

                        repassworde.setErrorEnabled(true);
                        repassworde.setError("Password Not Match");
                        repassworde.requestFocus();
                        editpassword.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                passworde.setErrorEnabled(false);
                                repassworde.setErrorEnabled(false);

                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        }


                        );
                        repassword.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                repassworde.setErrorEnabled(false);

                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });


                    }



        }}});

        btnsignins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivitysignin3.this, MainActivitysignin2.class);
                startActivity(intent);
            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidphone(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches());
    }
}
