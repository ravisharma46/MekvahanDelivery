package com.example.apple.mekvahandelivery.customer_report;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.mekvahandelivery.R;

public class customer_report extends AppCompatActivity {


    private FrameLayout car,bike;
    private ImageView car_image,bike_image;
    private TextView tvbike,tvcar,document;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_report);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Customer report</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        car=(FrameLayout) findViewById(R.id.frame_2);
        bike=(FrameLayout) findViewById(R.id.frame_1);
        car_image=(ImageView) findViewById(R.id.car_image);
        bike_image=(ImageView) findViewById(R.id.bike_image);
        tvbike=(TextView) findViewById(R.id.tvbike);
        tvcar=(TextView)findViewById(R.id.tvcar);
        document=(TextView)findViewById(R.id.tvDocument);

        loadCarFragment();
        load_car();

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCarFragment();
                load_car();



            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadBikeFragment();
               load_bike();


            }
        });


    }


    private void load_bike(){
        bike.setBackgroundResource(R.color.chart_deep_red);
        tvbike.setTextColor(Color.WHITE);
        bike_image.setImageResource(R.drawable.bike_white);

        car.setBackgroundResource(R.color.white);
        tvcar.setTextColor(Color.BLACK);
        car_image.setImageResource(R.drawable.carblack);
        document.setText("Bike document");
    }
    private void load_car(){
        bike.setBackgroundResource(R.color.white);
        tvbike.setTextColor(Color.BLACK);
        bike_image.setImageResource(R.drawable.bike_black);

        car.setBackgroundResource(R.color.chart_deep_red);
        tvcar.setTextColor(Color.WHITE);
        car_image.setImageResource(R.drawable.car_white);
        document.setText("Car document");
    }


    private  void loadCarFragment(){
        car_fragment c_Fragment=new car_fragment();
        android.support.v4.app.FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frameLayout,
                c_Fragment,
                c_Fragment.getTag()).commit();
    }

    private void loadBikeFragment(){
        bike_fragment b_Fragment=new bike_fragment();
        android.support.v4.app.FragmentManager manager= getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frameLayout,
                b_Fragment,
                b_Fragment.getTag()).commit();
    }



}
