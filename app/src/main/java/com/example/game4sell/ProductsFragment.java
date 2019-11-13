package com.example.game4sell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.game4sell.Model.Products;
import com.example.game4sell.ViewHolder.ProductsAdapter;
import com.example.game4sell.ViewHolder.RecyclerViewAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsFragment extends Fragment {

    public String userid;
    TextView userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        Bundle bundle = getArguments();

        final NavigationActivity activity = (NavigationActivity)getActivity();

        Bundle results = activity.getUserID();
        userid = results.getString("val1");


        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        final ProductsAdapter listAdapter = new ProductsAdapter(this, activity.getAllProducts());

        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(listAdapter);

        return view;
    }
}
