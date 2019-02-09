package ca.viclick.photogalary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoGalleryAdaptor extends RecyclerView.Adapter <PhotoGalleryAdaptor.PhotoGalleryViewHolder>{

    private Context mContext;
    private ArrayList<PhotoItem> mPhotoItemList;

    public PhotoGalleryAdaptor(Context mContext, ArrayList<PhotoItem> mPhotoItemList) {
        this.mContext = mContext;
        this.mPhotoItemList = mPhotoItemList;
    }

    @NonNull
    @Override
    public PhotoGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.photo_item, parent, false);
        return new PhotoGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoGalleryViewHolder photoGalleryViewHolder, int i) {

        PhotoItem photoItem = mPhotoItemList.get(i);

        photoGalleryViewHolder.user.setText(photoItem.getmUser());
        String likes = "Likes :" +photoItem.getmLikes();
        photoGalleryViewHolder.likes.setText(likes);

        Picasso.get()
                .load(photoItem.getmImageUrl())
                .fit()
                .centerInside()
                .into(photoGalleryViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return mPhotoItemList.size();
    }

    public class PhotoGalleryViewHolder extends RecyclerView.ViewHolder{

        private ImageView photo;
        private TextView user;
        private TextView likes;

        public PhotoGalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo);
            user = itemView.findViewById(R.id.user);
            likes = itemView.findViewById(R.id.likes);

        }
    }

}
