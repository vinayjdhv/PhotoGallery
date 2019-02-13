package ca.viclick.photogallery.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ca.viclick.photogallery.model.PhotoItem;
import ca.viclick.photogallery.services.PhotoGalleryService;

public class PhotoRepository {

    private LiveData<List<PhotoItem>> mPhotoItemList;
    private PhotoGalleryService photoGalleryService;

    public PhotoRepository(Application application) {
        photoGalleryService = new PhotoGalleryService(application);
    }

    public LiveData<List<PhotoItem>> getPhotoItemList(String keyword) {
        mPhotoItemList = photoGalleryService.getmPhotoItemList(keyword);
        return mPhotoItemList;
    }
}
