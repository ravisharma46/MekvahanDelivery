package com.example.apple.mekvahandelivery.customer_pickup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.mekvahandelivery.R;

public class upcoming_booking_2_2 extends AppCompatActivity {

    private LinearLayout paint_linear;
    private TextView tvDetails;
    private ImageView call;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_booking_2_2);

        paint_linear=(LinearLayout)findViewById(R.id.linear_paint);
        tvDetails=(TextView)findViewById(R.id.tvDetails);
        call=(ImageView)findViewById(R.id.call);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>#123</font>"));
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.chart_deep_red), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        tvDetails.setOnClickListener(new View.OnClickListener() {
            int check=1;
            @Override
            public void onClick(View view) {

                if(check==1){
                    paint_linear.setVisibility(View.VISIBLE);
                    check=0;
                }
                else{
                    paint_linear.setVisibility(View.GONE);
                    check=1;
                }


            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:123456789"));
                startActivity(callIntent);
            }
        });




    }
}
