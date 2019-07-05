package com.example.apple.mekvahandelivery;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class user_profile extends AppCompatActivity {

    private Button change_passWord,bt_done;
    private FrameLayout account_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        change_passWord=(Button)findViewById(R.id.btChange_pass);
        account_details=(FrameLayout) findViewById(R.id.account_details);
        bt_done=(Button)findViewById(R.id.bt_done);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Profile</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        change_passWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        account_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(user_profile.this,show_account_details.class);
                startActivity(i);
            }
        });



        bt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(user_profile.this,ongoingbooking_3_2.class);
                startActivity(i);
            }
        });



    }

    public void changePassword() {

        final AlertDialog alertDialog;

        LayoutInflater inflater = LayoutInflater.from(user_profile.this);
        final View v = inflater.inflate(R.layout.dialog_change_password,null);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertDialog = new AlertDialog.Builder(user_profile.this,android.
                    R.style.Theme_DeviceDefault_Light_Dialog_MinWidth).create();
        } else {
            alertDialog = new AlertDialog.Builder(user_profile.this).create();
        }

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        alertDialog.setView(v);

        final EditText et_current_pass,et_new_pass, et_confirm_pass;
        final TextView password_error;
        TextView done,cancel;


        et_current_pass = v.findViewById(R.id.current_password);
        et_new_pass     = v.findViewById(R.id.new_password);
        et_confirm_pass = v.findViewById(R.id.confirm_password);


        password_error = v.findViewById(R.id.tv_password_error);

        done   = v.findViewById(R.id.done);
        cancel = v.findViewById(R.id.cancel);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentPass,newPass,confirmPass;

                currentPass = et_current_pass.getText().toString().trim();
                newPass     = et_new_pass.getText().toString().trim();
                confirmPass = et_confirm_pass.getText().toString().trim();


                if(currentPass.equals("") || newPass.equals("") ||
                        confirmPass.equals("")){
                    password_error.setVisibility(View.VISIBLE);
                    password_error.setText("All field are required");
                    return;
                }


                //if old password is wrong
//                if(!currentPass.equals(mLoginSession.getUserDetailsFromSP()
//                        .get(KEY_PASSWORD))){
//                    password_error.setVisibility(View.VISIBLE);
//                    password_error.setText("*Current password is wrong");
//                    et_new_pass.setText("");
//                    et_confirm_pass.setText("");
//
//                    return;
//                }



                //if new password is shorter than six character
                if(newPass.length()<6){
                    password_error.setVisibility(View.VISIBLE);
                    password_error.setText("*New password must be of at least six characters");
                    et_confirm_pass.setText("");
                    return;
                }

                if(!newPass.equals(confirmPass)){
                    password_error.setVisibility(View.VISIBLE);
                    password_error.setText("*New password and confirm password do not match");
                    et_confirm_pass.setText("");
                    return;
                }

                password_error.setVisibility(View.GONE);

                //Toast.makeText(EditProfile.this,"new Pass "+newPass,Toast.LENGTH_SHORT).show();

                //et_password.setText(newPass);
                //sendNewPasswordToDataBase(newPass);
                alertDialog.dismiss();

            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }


}
