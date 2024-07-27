package com.example.warelineassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.warelineassignment.adapters.ImageAdapter;
import com.example.warelineassignment.models.ImageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView imageRecyclerView;
    List<ImageModel> imageList;
    ImageAdapter imageAdapter;
    boolean isScrolling = false;
    int currentItems,totalItems, scrollOutItems;
    ProgressBar progressBar;

    int pageNumber =1;
    String url = "https://api.pexels.com/v1/curated?page"+pageNumber+"&per_page=80";// here we need to load more pages while scrolling.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageRecyclerView = findViewById(R.id.imageRecyclerView);
        imageList = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, imageList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        imageRecyclerView.setLayoutManager(gridLayoutManager);

        //Scrolling behaviour
        imageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems)){
                    isScrolling = false;
                    fetchImage();
                }
            }
        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        fetchImage();

        imageRecyclerView.setAdapter(imageAdapter);
    }

    private void fetchImage() {
        //This function is used to fetch the image form the pixels library api
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("photos");

                            int length = jsonArray.length();

                            for(int i =0; i< length; i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                int id = (int) object.get("id");

                                JSONObject objectImage = object.getJSONObject("src");
                                String originalUrl = objectImage.getString("original");
                                String mediumUrl = objectImage.getString("medium");

                                ImageModel model = new ImageModel(originalUrl, mediumUrl, id);
                                imageList.add(model);
                            }

                            imageAdapter.notifyDataSetChanged();
                            pageNumber++;

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Authorization","MNUX97WG3UZOrx8rYqNXIa5NazncRi26KfegTYpnuRGR1ZJxskEYZNKi");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}