package com.example.game4sell.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game4sell.Model.Products;
import com.example.game4sell.ProductViewActivity;
import com.example.game4sell.ProductsFragment;
import com.example.game4sell.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Products> mData;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(ProductsFragment mContext, List<Products> mData) {

        this.mData = mData;
    }

    public RecyclerViewAdapter(Context mContext, List<Products> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product,
                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.product_Title.setText(mData.get(position).getTitle());
        holder.product_Thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductViewActivity.class);
                intent.putExtra("ProductTitle", mData.get(position).getTitle());
                intent.putExtra("Description", mData.get(position).getDescription());
                intent.putExtra("Price", mData.get(position).getPrice());
                intent.putExtra("Image", mData.get(position).getThumbnail());

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView product_Title;
        ImageView product_Thumbnail;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            product_Title = (TextView) itemView.findViewById(R.id.product_name_id);
            product_Thumbnail = (ImageView) itemView.findViewById(R.id.product_image_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

    public void refresh(){
        notifyDataSetChanged();
        Log.i("recycle", "test");
    }
}
