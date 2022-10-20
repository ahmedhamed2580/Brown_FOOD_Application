package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivitysignin2 extends AppCompatActivity {
    TextView btnsignup;
    EditText emailo,passwordo;
    Button signin;
    DBHELPER dp;
    TextInputLayout emilp ,passwordp;
    public static String e1,e2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsignin2);
        btnsignup=findViewById(R.id.btnsignup);
        emailo=findViewById(R.id.edit_sign_email);
        passwordo=findViewById(R.id.edit_sign_password);
        signin=findViewById(R.id.imagesign);
        emilp=findViewById(R.id.emailedittextel);
        passwordp=findViewById(R.id.passwordedittextel);
        dp=new DBHELPER(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elogin=emailo.getText().toString();
                e1=elogin;
                String plogin=passwordo.getText().toString();
                e2=plogin;
                if(TextUtils.isEmpty(elogin)||TextUtils.isEmpty(plogin)){
                    Toast.makeText(MainActivitysignin2.this, "All fildes Required", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(elogin)) {
                        emilp.setErrorEnabled(true);
                        emilp.setError("Filed Requierd");
                        emilp.requestFocus();
                    }
                    if(TextUtils.isEmpty(plogin)) {
                        passwordp.requestFocus();
                        passwordp.setErrorEnabled(true);
                        passwordp.setError("Filed Requierd");
                    }
                }

                else{
                    Boolean checkemailpassword=dp.checkemailpassword(elogin,plogin);
                    if(checkemailpassword==true){
                        Toast.makeText(MainActivitysignin2.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent( MainActivitysignin2.this, home_activty.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slideright_in_activty,R.anim.slideleft_out_activty);
                    }

                    else{
                        Toast.makeText(MainActivitysignin2.this, "Login Failled", Toast.LENGTH_SHORT).show();

                    }
                    emailo.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            emilp.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    passwordo.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            passwordp.setErrorEnabled(false);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivitysignin2.this, MainActivitysignin3.class);
                startActivity(intent);
            }
        });
    }
}