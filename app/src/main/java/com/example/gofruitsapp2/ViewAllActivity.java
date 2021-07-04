package com.example.gofruitsapp2;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gofruitsapp2.adapter.ViewAllAdapter;
import com.example.gofruitsapp2.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    ViewAllAdapter viewAllAdapter;
    Toolbar toolbar;
    List<ViewAllModel> viewAllModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra( "type" );
        recyclerView.findViewById(R.id.view_all_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter( this, viewAllModelList);

        recyclerView.setAdapter(viewAllAdapter);
        //get fruits
        if (type != null && type.equalsIgnoreCase( "fruits")){
            firestore. collection( "AllProducts").whereEqualTo("type", "fruits").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}