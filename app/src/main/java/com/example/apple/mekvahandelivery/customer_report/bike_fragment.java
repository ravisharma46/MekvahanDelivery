package com.example.apple.mekvahandelivery.customer_report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.mekvahandelivery.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bike_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bike_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bike_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bike_fragment, container, false);
    }

}
