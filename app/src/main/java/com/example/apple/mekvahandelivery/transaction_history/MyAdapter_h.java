package com.example.apple.mekvahandelivery.transaction_history;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.mekvahandelivery.R;

public class MyAdapter_h extends RecyclerView.Adapter<MyAdapter_h.ViewHolder> {

    //   private List<Listitem> listitems;
    private Context context;
    //ValueFilter valueFilter;

    public MyAdapter_h( Context context) {
        //this.listitems = listitems;

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_history_list,viewGroup,false);
        return new ViewHolder(v);




    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        final Listitem listitem=listitems.get(i);
//
//        viewHolder.textViewname.setText(listitem.getName());
//        viewHolder.textViewrent.setText(listitem.getRent_from());
//        viewHolder.textViewdeposite.setText(listitem.getSecurity_deposit_from());
//        viewHolder.textViewaccomd.setText(listitem.getAccomodation_allowed_str());
//        viewHolder.textViewbed.setText(listitem.getAvailable_bed_count()+" Beds");
//
//        String state_city= listitem.getCity()+","+listitem.getState();
//        viewHolder.textViewcity.setText(state_city);


       viewHolder.textViewtotal.setText("Total "+"\u20B9"+" 100.00");

       viewHolder.textViewdetail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i= new Intent(view.getContext(),transaction_1.class);
               view.getContext().startActivity(i);

           }
       });









    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewtotal,textViewdetail,textViewstate,textViewrent,textViewdeposite,textViewaccomd,textViewbed;
        private ImageView imageView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           textViewtotal= (TextView) itemView.findViewById(R.id.tvTotal);
           textViewdetail= (TextView) itemView.findViewById(R.id.tvDetails);
//            textViewrent= (TextView) itemView.findViewById(R.id.rent_tv);
//            textViewdeposite= (TextView) itemView.findViewById(R.id.deposite_tv);
//            textViewaccomd= (TextView) itemView.findViewById(R.id.accomd_tv);
//            textViewbed= (TextView)itemView.findViewById(R.id.bed_tv);
//
//            imageView =(ImageView) itemView.findViewById(R.id.image_View);
//            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }



}