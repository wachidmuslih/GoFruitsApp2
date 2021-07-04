package com.example.gofruitsapp2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gofruitsapp2.R;
import com.example.gofruitsapp2.adapter.HomeAdapter;
import com.example.gofruitsapp2.adapter.PopularAdapter;
import com.example.gofruitsapp2.adapter.RecommedationAdapter;
import com.example.gofruitsapp2.models.HomeCategory;
import com.example.gofruitsapp2.models.RecommendationModels;
import com.example.gofruitsapp2.models.popularModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRec, homeCatReg, recommendedRec;
    FirebaseFirestore db;

    //popular item
    List<popularModels> popularModelsList;
    PopularAdapter popularAdapter;

    //home category
    List<HomeCategory> homeCategoryList;
    HomeAdapter homeAdapter;
    
    //recommended
    List<RecommendationModels> recommendationModelsList;
    RecommedationAdapter recommedationAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        popularRec = root.findViewById(R.id.pop_rec);
        homeCatReg = root.findViewById(R.id.eksplore_rec);
        recommendedRec = root.findViewById(R.id.recommeded_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressBar);

        scrollView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        db = FirebaseFirestore.getInstance();

        //popular item
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelsList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelsList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                popularModels popularModels = document.toObject(com.example.gofruitsapp2.models.popularModels.class);
                                popularModelsList.add(popularModels);
                                popularAdapter.notifyDataSetChanged();

                                scrollView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Home Category
        homeCatReg.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeCategoryList = new ArrayList<HomeCategory>();
        homeAdapter = new HomeAdapter(getActivity(), homeCategoryList);
        homeCatReg.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                homeCategoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Recommended
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recommendationModelsList = new ArrayList<>();
        recommedationAdapter = new RecommedationAdapter(getActivity(), recommendationModelsList);
        recommendedRec.setAdapter(recommedationAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendationModels recommendationModels= document.toObject(RecommendationModels.class);
                                recommendationModelsList.add(recommendationModels);
                                recommedationAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}