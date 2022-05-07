package com.example.shop4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4.Global.Link;
import com.example.shop4.R;
import com.example.shop4.adapter.CategoryAdapter;
import com.example.shop4.adapter.SliderAdapter;
import com.example.shop4.model.Banner;
import com.example.shop4.model.Category;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    View view;
    RequestQueue requestQueue;
    //Slider
    List<Banner> listBanner = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ViewPager viewPager;
    TabLayout tabs;

    //Category
    List<Category> listCategory = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerviewCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        requestQueue = Volley.newRequestQueue(getContext());
        getBannerSlider();
        getCategory();
        showCategories();
        return view;
    }

    private void getCategory() {

        String url = Link.LINK_CATEGORY_BY_LIMIT;

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Category[] categories = gson.fromJson(response.toString() ,  Category[].class);

                for (int i = 0 ; i<categories.length ; i++){

                    listCategory.add(categories[i]);
                    categoryAdapter.notifyDataSetChanged();
                    Log.e("CHeck"," : "+ categories[i]);

                }
                showCategories();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
                Log.e("Error : " , error.getMessage()+" ");

            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET , url ,null ,listener , errorListener );
        requestQueue.add(request);


    }

    private void showCategories(){
        recyclerviewCategory = view.findViewById(R.id.recyclerView_Category);
        recyclerviewCategory.setHasFixedSize(true);
        recyclerviewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Category> listCategory2 = new ArrayList<>();
        listCategory2.add(new Category("357","HI","https://google.com"));
        listCategory2.add(new Category("357","HI","https://google.com"));
        listCategory2.add(new Category("357","HI","https://google.com"));
        categoryAdapter = new CategoryAdapter(getContext(), listCategory2);
        recyclerviewCategory.setAdapter(categoryAdapter);
    }
    private void getBannerSlider() {
        viewPager = view.findViewById(R.id.viewPager);
        tabs = view.findViewById(R.id.tabs);
        sliderAdapter = new SliderAdapter(getContext() , listBanner);
        viewPager.setAdapter(sliderAdapter);
        tabs.setupWithViewPager(viewPager , true);

        viewPager.setRotationY(180);

        final int paddingPx = 120;
        final float MIN_SCALE = 0.8f;
        final float MAX_SCALE = 1f;

        viewPager.setClipToPadding(false);
        viewPager.setPadding(paddingPx, 0, paddingPx, 0);

        ViewPager.PageTransformer transformer = new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

                float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
                float pageWidthPx = pagerWidthPx - 2 * paddingPx;

                float maxVisiblePages = pagerWidthPx / pageWidthPx;
                float center = maxVisiblePages / 2f;

                float scale;
                if (position + 0.5f < center - 0.5f || position > center) {
                    scale = MIN_SCALE;
                } else {
                    float coef;
                    if (position + 0.5f < center) {
                        coef = (position + 1 - center) / 0.5f;
                    } else {
                        coef = (center - position) / 0.5f;
                    }
                    scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
                }
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
        };
        viewPager.setPageTransformer(false, transformer);

        String url = Link.LINK_BANNER_SLIDER;

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new Gson();
                Banner[] banners = gson.fromJson(response.toString() ,  Banner[].class);

                for (int i = 0 ; i<banners.length ; i++){

                    listBanner.add(banners[i]);
                    sliderAdapter.notifyDataSetChanged();

                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
                Log.d("Error : " , error.getMessage()+"");

            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET , url ,null ,listener , errorListener );
        requestQueue.add(request);
    }
}