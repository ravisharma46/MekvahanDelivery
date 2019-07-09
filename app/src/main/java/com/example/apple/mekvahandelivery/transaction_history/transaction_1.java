package com.example.apple.mekvahandelivery.transaction_history;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.mekvahandelivery.R;

public class transaction_1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FrameLayout car,bike;
    private ImageView car_image,bike_image;
    private TextView tvbike,tvcar,document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Details</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        car=(FrameLayout) findViewById(R.id.frame_2);
        bike=(FrameLayout) findViewById(R.id.frame_1);
        car_image=(ImageView) findViewById(R.id.car_image);
        bike_image=(ImageView) findViewById(R.id.bike_image);
        tvbike=(TextView) findViewById(R.id.tvbike);
        tvcar=(TextView)findViewById(R.id.tvcar);

        //load_bike();

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_car();
            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_bike();
            }
        });




        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



            loadRecyclerViewData();





    }
    private void load_bike(){
        bike.setBackgroundResource(R.color.chart_deep_red);
        tvbike.setTextColor(Color.WHITE);
        bike_image.setImageResource(R.drawable.bike_white);

        car.setBackgroundResource(R.color.white);
        tvcar.setTextColor(Color.BLACK);
        car_image.setImageResource(R.drawable.carblack);

    }
    private void load_car(){
        bike.setBackgroundResource(R.color.white);
        tvbike.setTextColor(Color.BLACK);
        bike_image.setImageResource(R.drawable.bike_black);

        car.setBackgroundResource(R.color.chart_deep_red);
        tvcar.setTextColor(Color.WHITE);
        car_image.setImageResource(R.drawable.car_white);

    }

    private   void loadRecyclerViewData(){



        adapter= new MyAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
