package com.example.game4sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.game4sell.Database.ProductDBHelper;
import com.example.game4sell.Model.Products;
import com.example.game4sell.Model.User;
import com.example.game4sell.ViewHolder.ProductsAdapter;
import com.example.game4sell.ViewHolder.RecyclerViewAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SQLiteDatabase mDatabase;
    ProductDBHelper dbHelper;
    private ProductsAdapter mAdapter;
    String userid, userName, userNumber, userAddress, userPassword;

    ArrayList<Products> listProduct;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        dbHelper = new ProductDBHelper(this);
        final ProductsFragment product = new ProductsFragment();

        Intent intent = getIntent();
        userid = intent.getExtras().getString("UserID");

        ContentValues cv = new ContentValues();
        cv.put(ProductDBHelper.COL_TITLE, "Borderlands 3");
        cv.put(ProductDBHelper.COL_CATEGORY, "Action");
        cv.put(ProductDBHelper.COL_DESCRIPTION, "This is borderlands 3");
        cv.put(ProductDBHelper.COL_IMAGE, R.drawable.borderlands_3);
        cv.put(ProductDBHelper.COL_PRICE, 59.99);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProductsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_products);
            getSupportActionBar().setTitle("Products");
        }
    }

    public Bundle getUserID(){
        Bundle hm = new Bundle();
        hm.putString("userID",userid);
        return hm;
    }

    public void addProduct(String title, String categories, String description, Integer image, Double price ){
        boolean result = dbHelper.addProduct(new Products(title, categories, description,image,price));
    }

    public Cursor getAllProducts(){
        mDatabase = dbHelper.getWritableDatabase();
        return mDatabase.query(
                ProductDBHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                getSupportActionBar().setTitle("Profile");
                break;

            case R.id.nav_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProductsFragment()).commit();
                getSupportActionBar().setTitle("Products");
                break;

            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoriesFragment()).commit();
                getSupportActionBar().setTitle("Categories");
                break;

            case R.id.nav_sell:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SellFragment()).commit();
                getSupportActionBar().setTitle("Sell");
                break;

            case R.id.nav_wishlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WishlistFragment()).commit();
                break;

            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    public void showAll() {
        Cursor cursor = dbHelper.getAll();

        if(cursor.getCount() == 0){
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Id: " + cursor.getString(0) + "\n");
            buffer.append("Email: " + cursor.getString(1) + "\n");
            buffer.append("Password: " + cursor.getString(2) + "\n");
            buffer.append("Username: " + cursor.getString(3) + "\n");
            buffer.append("Address: " + cursor.getString(4) + "\n");
            buffer.append("Number: " + cursor.getInt(5)/100.0 + "\n");
            buffer.append("_________________________" + "\n");

        }
        showMessage("Data", buffer.toString());
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
}
