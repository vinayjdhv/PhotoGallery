package ca.viclick.photogalary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoGalleryActivity extends AppCompatActivity {


    private RequestQueue mRequestQueue;
    private ArrayList<PhotoItem> mPhotoItemList;
    private PhotoGalleryAdaptor mPhotoGalleryAdaptor;
    private RecyclerView mRecyclerViewPhotos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallary);

        mRecyclerViewPhotos = findViewById(R.id.recycler_view_photos);
        mRecyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));

        mPhotoItemList = new ArrayList<>();

        Intent intent = getIntent();
        String keyword = intent.getStringExtra("KEY_WORD");
        getPhotos(keyword);
    }

    private void getPhotos(String keyWord) {

        String url = "https://pixabay.com/api/?key=11534654-995bd670e046d1afdd0e97daa&image_type=photo&pretty=true&q="+ keyWord;

        mRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                responseListener,
                errorListener);

        mRequestQueue.add(jsonObjectRequest);

    }


    Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            try {
                JSONArray jsonArray = response.getJSONArray("hits");

                for (int i = 0; i < jsonArray.length() ; i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String imageUrl = jsonObject.getString("previewURL");
                    int likes = jsonObject.getInt("likes");
                    String user = jsonObject.getString("user");
                    mPhotoItemList.add(new PhotoItem(imageUrl, user, likes));

                }

                mPhotoGalleryAdaptor = new PhotoGalleryAdaptor(PhotoGalleryActivity.this,
                        mPhotoItemList);
                mRecyclerViewPhotos.setAdapter(mPhotoGalleryAdaptor);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    };

}
