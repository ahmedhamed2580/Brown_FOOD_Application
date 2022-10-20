package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.w3c.dom.Text;

public class dialog {


        public void showDialog(Activity activity, String msg, String details){
            activity.getWindow().getAttributes().windowAnimations=R.style.animation;

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog);

            TextView text = (TextView) dialog.findViewById(R.id.subject_dialog);
            TextView text2=(TextView)dialog.findViewById(R.id.details_dialog);
            text.setText(msg);
            text2.setText(details);

            ConstraintLayout dialogButton = (ConstraintLayout) dialog.findViewById(R.id.submit_dialog);
            TextView dialogButtoncancel = (TextView) dialog.findViewById(R.id.cancel_dailog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialogButtoncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();

                }
            });


            dialog.show();


    }
}
