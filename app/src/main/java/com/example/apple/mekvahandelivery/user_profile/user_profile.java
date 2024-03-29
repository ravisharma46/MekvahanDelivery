package com.example.apple.mekvahandelivery.user_profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apple.mekvahandelivery.R;
import com.example.apple.mekvahandelivery.customer_pickup.ongoingbooking_3_2;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_profile extends AppCompatActivity {

    private Button change_passWord,bt_done;
    private FrameLayout account_details;
    private TextView name,mobile,email,address,partnerType,executive_id,name_1,update_pic;
    private static final String myUrl="https://mekvahan.com/api/delivery/deliveryBoy";
   //private static final String myUrl="https://mekvahan.com/api/user/delivery/completeDelivery";
    private CircleImageView imageView;
    private static final int GALLARY_REQUEST = 1;
    private int mFlag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        change_passWord=(Button)findViewById(R.id.btChange_pass);
        account_details=(FrameLayout) findViewById(R.id.account_details);
        bt_done=(Button)findViewById(R.id.bt_done);

        imageView=(CircleImageView) findViewById(R.id.image_icon);
        name=(TextView) findViewById(R.id.tvname);
        name_1=(TextView) findViewById(R.id.tvname1);
        mobile=(TextView) findViewById(R.id.tvmobile);
        email=(TextView) findViewById(R.id.tvemail);
        address=(TextView) findViewById(R.id.tvaddress);
        partnerType=(TextView) findViewById(R.id.tvpartner);
        executive_id=(TextView) findViewById(R.id.tvprofileid);
        update_pic=(TextView) findViewById(R.id.tvupdatepic);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Profile</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);



        update_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(user_profile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(user_profile.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    return;
                }

                mFlag = 2;
                Intent gallaryIntent = new Intent(Intent.ACTION_PICK);
                gallaryIntent.setType("image/*");
                gallaryIntent.putExtra("flag",1);

                startActivityForResult(gallaryIntent,GALLARY_REQUEST);
            }
        });


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

         //userLogin();

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


    public void userLogin(){





        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        //----if everything is fine-----
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            //converting response to json object

                          //  Log.e("TAGR",response);


                            JSONObject obj= new JSONObject(response);
                            JSONObject object2 = obj.getJSONObject("DeliveryBoy details");
                            Log.e("TAG",response);

                            name.setText("Mr."+object2.getString("name"));
                            name_1.setText(object2.getString("name"));
                            mobile.setText("+91-"+object2.getString("mobile"));
                            email.setText(object2.getString("email"));
                            partnerType.setText(object2.getString("type"));
                            executive_id.setText(object2.getString("profile_id"));

//                            Picasso.with(getApplicationContext())
//                                    .load(object2.getString("picture"))
//                                    .into(imageView);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Eror in login", Toast.LENGTH_SHORT).show();
                        Log.e("TAGR",error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

              // params.put("delivery_boy_id","1");
                params.put("mobile", "9911202111");
                params.put("password", "mridul");
                params.put("provider", "deliverys");

                Log.e("TAG",params.toString());
                return params;
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //params.put("Content-Type","application/json");
               ///params.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjMzZWMxMGYwOWVlYjUzMWRkOTUxZDk1NmRhZjg0M2JiNjE2YTI5NmFkZTRjMmNiZmUxM2ZiOTM3NTBkMGI2NDg3YjkxNWIyODRkMzdmOGIwIn0.eyJhdWQiOiIxIiwianRpIjoiMzNlYzEwZjA5ZWViNTMxZGQ5NTFkOTU2ZGFmODQzYmI2MTZhMjk2YWRlNGMyY2JmZTEzZmI5Mzc1MGQwYjY0ODdiOTE1YjI4NGQzN2Y4YjAiLCJpYXQiOjE1NTQ0Njg3NjAsIm5iZiI6MTU1NDQ2ODc2MCwiZXhwIjoxNTg2MDkxMTYwLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.TKvyw2oWee5RYwt6VGFp4ae6Jp-BeRYq5fuvmcgCeKzE31h1yjPL_m1iBu0BrTN35GiH4_yUEq5xVpRtTgTa9IL823BTxHmF-1leOCPJz6e5yef6h0Bj8Wqtt4voijRmxtzAjyP8H3yXGcW-L1_mjoWjMKWSObAJ5NhmYwbbq3M148eSwMsyzLA5xIOlkN4Fdha1mM4yKZ259mPdIvZGPIA3d_TBM67wFVT26xi0lFvWfBgRzZMCGcx19dLDHY_4e9aBRoUOZ81JecStpWbmhg_cjTAncJcdReEzAW9s4yp9q4KmIWAaYdABsmNV9X5Ai9wFkBLtAXQtdCpXYOyNok6Q7xfKioCfx_UT2ZL93Yvgjg-ht5Ko3Tz-pqFPMocgdLu8-EwC3JY666rMZvk9bSCyXh2-0hRGB0IukFMSdsaETlG8jjJI8B93_2zqpHcTSpB-Ig9PUm0Ye0vKxAAhie7e3Ff2EfvExFZq3oNGNzbZbNEMjguu15wlJlGKaAKHmKRIg9y8D-MyAN4V5V3216sDrjr70lZ5w-GGxXJUEcCfECUQuscVB0wazZDGQiwA7wUboTpbuBVY19p58PzOCtvaxzgTNlNrd8EhShRFmhepev-dURcUqaDFaJprlqX7ysgqJGBXstwxwlYIYlR3oM8yZk05zWi4QjCpQNiFHCA");

                return super.getHeaders();
            }
        };


        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLARY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            imageView.setImageURI(imageUri);


        }
    }
}
