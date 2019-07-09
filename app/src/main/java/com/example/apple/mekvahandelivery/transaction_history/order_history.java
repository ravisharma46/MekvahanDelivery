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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.mekvahandelivery.R;

public class order_history extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView customer,service;
    private FrameLayout frameLayout_1,frameLayout_2;
    private LinearLayout ll_1,ll_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Order History</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        customer=(TextView)findViewById(R.id.tvCustomer);
        service=(TextView)findViewById(R.id.tvService_part);

        frameLayout_1=(FrameLayout)findViewById(R.id.frame_1);
        frameLayout_2=(FrameLayout)findViewById(R.id.frame_2);

        ll_1=(LinearLayout) findViewById(R.id.ll_1);
        ll_2=(LinearLayout) findViewById(R.id.ll_2);

        customer_listner();
        frameLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer_listner();
            }
        });
        frameLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service_listner();
            }
        });




        recyclerView =(RecyclerView) findViewById(R.id.recyclerView_1);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        loadRecyclerViewData();

    }

    private   void loadRecyclerViewData(){
        adapter= new MyAdapter_h(getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    private void customer_listner(){
        ll_1.setBackgroundResource(R.color.chart_deep_red);
        customer.setTextColor(getResources().getColor(R.color.chart_deep_red));

        ll_2.setBackgroundResource(R.color.app_caption_dark_grey);
        service.setTextColor(getResources().getColor(R.color.app_caption_dark_grey));

    }

    private void service_listner(){
        ll_2.setBackgroundResource(R.color.chart_deep_red);
        service.setTextColor(getResources().getColor(R.color.chart_deep_red));

        ll_1.setBackgroundResource(R.color.app_caption_dark_grey);
        customer.setTextColor(getResources().getColor(R.color.app_caption_dark_grey));

    }

}
