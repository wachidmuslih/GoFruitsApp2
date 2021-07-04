package com.example.gofruitsapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.gofruitsapp2.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    ImageView detailedImg;
    int totalQty = 1;
    int totalPrice = 0;
    TextView price, rating, description, quantity;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel)
        {
            viewAllModel = (ViewAllModel) object;
        }
        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);

        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById( R.id.detailed_rating);
        description = findViewById(R.id.detailed_desc);

        if (viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price : Rp" + viewAllModel.getPrice() +"/kg");

            totalPrice = viewAllModel.getPrice() * totalQty;
            if(viewAllModel.getType().equals("fruits")){
                price.setText("Price : Rp" + viewAllModel.getPrice() +"/kg");
                totalPrice = viewAllModel.getPrice() * totalQty;
            }
        }
        addToCart = findViewById(R.id.buttonadd);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();

            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQty < 10){
                    totalQty++;
                    quantity.setText(String.valueOf(totalQty));
                    totalPrice = viewAllModel.getPrice() * totalQty;
                }

            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQty > 10){
                    totalQty--;
                    quantity.setText(String.valueOf(totalQty));
                    totalPrice = viewAllModel.getPrice() * totalQty;
                }

            }
        });




    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance( );
        SimpleDateFormat currentDate = new SimpleDateFormat( "MM dd, yyyy" );
        saveCurrentDate = currentDate. format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat( "HH:mm:ss a" );
        saveCurrentTime = currentTime.format(calForDate.getTime());
        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("CurrentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailActivity.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


}