package ca.viclick.photogalary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ca.viclick.photogalary.model.PhotoItem;
import ca.viclick.photogalary.repository.PhotoRepository;

public class PhotoGalleryViewModel extends AndroidViewModel {

    private LiveData<List<PhotoItem>> mPhotoItemList;
    private PhotoRepository repository;

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);
        repository = new PhotoRepository(application);
    }


    public LiveData<List<PhotoItem>> getmPhotoItemList(String keyword) {
        mPhotoItemList = repository.getPhotoItemList(keyword);
        return mPhotoItemList;
    }


}
