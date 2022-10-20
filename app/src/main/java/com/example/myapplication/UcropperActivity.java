package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.yalantis.ucrop.UCrop;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.File;
import java.util.UUID;

public class UcropperActivity extends AppCompatActivity {
String sourceUri,desinationUri;
Uri uri;
DBHELPER db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucropper);
        db=new DBHELPER(this);
        Intent intent=getIntent();
        if (intent.getExtras()!=null){
            sourceUri=intent.getStringExtra("sedimentation");
            uri=Uri.parse(sourceUri);
        }
        desinationUri =new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        UCrop.Options options=new UCrop.Options();
        UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),desinationUri)))
                .withOptions(options)
               // .withAspectRatio(16, 9)
                .withMaxResultSize(2000,2000)
                .start(UcropperActivity.this);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULT_OK&&requestCode==UCrop.REQUEST_CROP){
            assert data != null;
            final Uri resultUri=UCrop.getOutput(data);
            Intent intent=new Intent();
            intent.putExtra("CROP",resultUri+ "");
            setResult(101,intent);



            finish();
        } else if (resultCode==UCrop.RESULT_ERROR){
            final Throwable cropError=UCrop.getError(data);

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(UcropperActivity.this, profile.class);
        startActivity(intent);
    }
}