package ca.viclick.photogalary.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ca.viclick.photogalary.adaptor.PhotoGalleryAdaptor;
import ca.viclick.photogalary.R;
import ca.viclick.photogalary.model.PhotoItem;
import ca.viclick.photogalary.viewmodel.PhotoGalleryViewModel;

public class PhotoGalleryActivity extends AppCompatActivity {

    private PhotoGalleryAdaptor mPhotoGalleryAdaptor;
    private RecyclerView mRecyclerViewPhotos;
    private PhotoGalleryViewModel photoGalleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallary);


        Intent intent = getIntent();
        String keyword = intent.getStringExtra("KEY_WORD");

        mRecyclerViewPhotos = findViewById(R.id.recycler_view_photos);
        mRecyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));

        mPhotoGalleryAdaptor = new PhotoGalleryAdaptor();
        mRecyclerViewPhotos.setAdapter(mPhotoGalleryAdaptor);

        photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel.class);
        photoGalleryViewModel.getmPhotoItemList(keyword).observe(this, new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(@Nullable List<PhotoItem> photoItems) {
                mPhotoGalleryAdaptor.setmPhotoItemList(photoItems);
            }
        });
    }


}
