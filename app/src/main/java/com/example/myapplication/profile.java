package com.example.myapplication;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.sql.Blob;
import java.util.ArrayList;

import static com.example.myapplication.MainActivitysignin2.*;

public class profile extends AppCompatActivity {
    ImageView camera,imagecam;
    ActivityResultLauncher<String>cropimage;
    ArrayList<profileinform> profileinformArrayList = new ArrayList<>();
    SQLiteDatabase db2;
    DBHELPER db;
    ConstraintLayout constraintLayout;

    mydatabase mydatabase;
    mydatabase2 mydatabase2;
    TextView nametextview,emailtextview;
    EditText editTextadress;
    EditText editphone,editprofilename,help,contact;
    TextView logout;
    ImageView editprofile;
    public static Uri uri;
    LinearLayout ln,lo,lp,ho;
    public static FrameLayout frameLayout;
    public static TextView numberitems;
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nametextview=findViewById(R.id.nametextview);
      editTextadress=findViewById(R.id.adressedittext);
      editphone=findViewById(R.id.phoneedittext2);
        emailtextview=findViewById(R.id.emailtextview);
        editprofile=findViewById(R.id.editprofile);
        camera=findViewById(R.id.camera);
        imagecam=findViewById(R.id.imagecam);
        editprofilename=findViewById(R.id.editnameprofile);
        help=findViewById(R.id.help);
        contact=findViewById(R.id.contactus);
        lo=findViewById(R.id.lovebtn);
        lp=findViewById(R.id.settingsbtn);
        ln=findViewById(R.id.menubtn);
        ho=findViewById(R.id.homebtn);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        frameLayout= findViewById(R.id.frameLayoutnotf);
        numberitems=findViewById(R.id.numberdata);
         mydatabase =new mydatabase(this);
         mydatabase2=new mydatabase2(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, MainActivity3.class);
                startActivity(intent);
            }
        });
         ho.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(profile.this, home_activty.class);
                 startActivity(intent);
             }
         });
        if( mydatabase.getfoodcount()>0){
            frameLayout.setVisibility(View.VISIBLE);
            mydatabase.getfoodcount();



            String countf= String.valueOf(mydatabase.getfoodcount());
            numberitems.setText(countf);
        }
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this,Menu.class);
                startActivity(intent);
            }
        });
        lp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, profile.class);
                startActivity(intent);

            }
        });
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydatabase2.getfoodcount()!=0) {
                    Intent intent = new Intent(profile.this, Loves_Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(profile.this, "Add items to The Loves List First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Clicked", Toast.LENGTH_SHORT).show();
                openWhatsApp("1001590779");
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Clicked", Toast.LENGTH_SHORT).show();
                openWhatsApp("1001590779");
            }
        });
        logout=findViewById(R.id.logout);
            mydatabase=new mydatabase(this);
            mydatabase2=new mydatabase2(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(profile.this, MainActivitysignin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideright_in_activty, R.anim.slideleft_out_activty);
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cropimage=registerForActivityResult(new ActivityResultContracts.GetContent(),result->{
            Intent intent=new Intent(profile.this.getApplicationContext(),UcropperActivity.class);
if(result!=null) {
    intent.putExtra("sedimentation", result.toString());
    startActivityForResult(intent, 100);
}
        });
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imagepermision();

            }
        });
        db=new DBHELPER(this);
        Cursor cursor = new DBHELPER(this).getdata(e1,e2);
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(0);
                String nameprof = cursor.getString(2);
                String adresse = cursor.getString(3);
                String phone = cursor.getString(4);

                if ((cursor.getString(5))!= null){
                    if(Uri.parse(cursor.getString(5))!=null) {

                        Uri urs = Uri.parse(cursor.getString(5));
                        imagecam.setImageURI(urs);
                    }
            }


                emailtextview.setText(email);
               nametextview.setText(nameprof);
               editTextadress.setText(adresse);
               editphone.setText(phone);
               editprofilename.setText(nameprof);

            } while (cursor.moveToNext());
        }


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(editprofile.getTag()=="e1"){
               enableFildes();
           }
           else{
               disableFildes();
           }

           db.updatedata(emailtextview.getText().toString(),nametextview.getText().toString(),editTextadress.getText().toString(),editphone.getText().toString());
            }
        });
    }



  private  void imagepermision(){
      Dexter.withContext(profile.this)
              .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
              .withListener(new PermissionListener() {
                  @Override
                  public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                      cropimage.launch("image/*");
                  }

                  @Override
                  public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                      Toast.makeText(profile.this, "Premisson Denied", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                  }
              }).check();
  }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            String result = data.getStringExtra("CROP");
              uri = data.getData();
            if (result != null) {
                uri = Uri.parse(result);
                db.updatedata2(uri.toString(),emailtextview.getText().toString());
                imagecam.setImageURI(uri);

            }







            }
        }


private  void enableFildes(){
    editTextadress.setEnabled(true);
    editphone.setEnabled(true);
    editprofile.setImageResource(R.drawable.ticks);
    editprofile.setTag("e2");
    editprofilename.setVisibility(View.VISIBLE);
    nametextview.setVisibility(View.GONE);
}
private  void disableFildes(){
    editTextadress.setEnabled(false);
    editphone.setEnabled(false);
    editprofile.setImageResource(R.drawable.edit);
    editprofile.setTag("e1");
    editprofile.setColorFilter(Color.argb(255,255,255,255));
    nametextview.setText(editprofilename.getText().toString());
    editprofilename.setVisibility(View.GONE);
    nametextview.setVisibility(View.VISIBLE);
}
    private void openWhatsApp(String number) {
        try {
            number = number.replace(" ", "").replace("+", "");

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number)+"@s.whatsapp.net");
            // getApplication().startActivity(sendIntent);

            startActivity(Intent.createChooser(sendIntent, "Compartir en")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        } catch(Exception e) {
            Log.e("WS", "ERROR_OPEN_MESSANGER"+e.toString());
        }
    }
}
