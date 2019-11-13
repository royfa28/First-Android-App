package com.example.game4sell.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game4sell.Database.ProductDBHelper;
import com.example.game4sell.Model.Products;
import com.example.game4sell.ProductViewActivity;
import com.example.game4sell.ProductsFragment;
import com.example.game4sell.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public ProductsAdapter(ProductsFragment mContext, Cursor cursor) {
        mContext = mContext;
        mCursor = cursor;
    }

    public ProductsAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)){
            return;
        }
        String title = mCursor.getString(mCursor.getColumnIndex(ProductDBHelper.COL_TITLE));
        int image = mCursor.getInt(mCursor.getColumnIndex((ProductDBHelper.COL_IMAGE)));
        String description = mCursor.getString(mCursor.getColumnIndex(ProductDBHelper.COL_DESCRIPTION));
        double price = mCursor.getDouble(mCursor.getColumnIndex(ProductDBHelper.COL_PRICE));

        holder.product_Title.setText(title);
        holder.product_Thumbnail.setImageResource(image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductViewActivity.class);
                intent.putExtra("ProductTitle", mCursor.getString(mCursor.getColumnIndex(ProductDBHelper.COL_TITLE)));
                intent.putExtra("Description", mCursor.getString(mCursor.getColumnIndex(ProductDBHelper.COL_DESCRIPTION)));
                intent.putExtra("Price",  mCursor.getDouble(mCursor.getColumnIndex(ProductDBHelper.COL_PRICE)));
                intent.putExtra("Image", mCursor.getInt(mCursor.getColumnIndex((ProductDBHelper.COL_IMAGE))));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }

        mCursor = newCursor;
        if(newCursor!= null){
            notifyDataSetChanged();
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView product_Title;
        public ImageView product_Thumbnail;
        public CardView cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_Title = (TextView) itemView.findViewById(R.id.product_name_id);
            product_Thumbnail = (ImageView) itemView.findViewById(R.id.product_image_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
