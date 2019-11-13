package com.example.game4sell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductViewActivity extends AppCompatActivity{

    private TextView productTitle, productDescription, productPrice;
    private Button addToCartButton;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Product Detail");

        addToCartButton = (Button) findViewById(R.id.addToCartButton);
        productTitle = (TextView) findViewById(R.id.textViewTitle);
        productDescription = (TextView) findViewById(R.id.textViewDescription);
        productPrice = (TextView) findViewById(R.id.textViewPrice);
        img = (ImageView) findViewById(R.id.productThumbnail);

        Intent intent = getIntent();
        final String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int Image = intent.getExtras().getInt("Image");
        final Double Price = intent.getExtras().getDouble("Price");

        productTitle.setText(Title);
        productPrice.setText(Price.toString());
        productDescription.setText(Description);
        img.setImageResource(Image);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductViewActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
