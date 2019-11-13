package com.example.game4sell;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.game4sell.Database.ProductDBHelper;
import com.example.game4sell.Model.Products;
import com.example.game4sell.ViewHolder.RecyclerViewAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SellFragment extends Fragment {

    Button sell;
    EditText gameName, gamePrice;
    ProductsFragment product = new ProductsFragment();
    ArrayList<Products> listProduct;

    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sell, container, false);

        gameName = (EditText)view.findViewById(R.id.gameNameText);
        gamePrice = (EditText)view.findViewById(R.id.priceText);
        sell = (Button)view.findViewById(R.id.sellButton);
        final NavigationActivity activity = (NavigationActivity)getActivity();
        //final RecyclerViewAdapter listAdapter = new RecyclerViewAdapter(this, listProduct);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameName.getText().toString().isEmpty() || gamePrice.getText().toString().isEmpty()){
                    Toast.makeText(activity, "All file need to be filled", Toast.LENGTH_SHORT).show();
                }else{
                    activity.addProduct(gameName.getText().toString(), "Adventure", "This is " + gameName.getText().toString() , R.drawable.borderlands_3, Double.parseDouble(gamePrice.getText().toString()));
                    Toast.makeText(activity, "Product Added", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new ProductsFragment());
                    fr.commit();
                }

            }
        });
        return view;
    }

}
