package ca.viclick.photogalary.services;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
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
import java.util.List;

import ca.viclick.photogalary.model.PhotoItem;

public class PhotoGalleryService {


    private MutableLiveData<List<PhotoItem>> mPhotoItemList;
    private Context context;

    public PhotoGalleryService(Context context) {
        this.context = context;
        mPhotoItemList = new MutableLiveData<>();
    }

    public LiveData<List<PhotoItem>> getmPhotoItemList(String keyword) {
        getPhotos(keyword);
        return mPhotoItemList;
    }

    private void getPhotos(String keyWord) {

        String url = "https://pixabay.com/api/?key=11534654-995bd670e046d1afdd0e97daa&image_type=photo&pretty=true&q="+ keyWord;

        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

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

            List<PhotoItem> itemList = new ArrayList<>();
            try {
                JSONArray jsonArray = response.getJSONArray("hits");

                for (int i = 0; i < jsonArray.length() ; i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String imageUrl = jsonObject.getString("previewURL");
                    int likes = jsonObject.getInt("likes");
                    String user = jsonObject.getString("user");
                    itemList.add(new PhotoItem(imageUrl, user, likes));

                }
                mPhotoItemList.postValue(itemList);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    };

}
