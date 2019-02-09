package ca.viclick.photogalary;

public class PhotoItem {

    private String mImageUrl;
    private String mUser;
    private int mLikes;

    public PhotoItem(String mImageUrl, String mUser, int mLikes) {
        this.mImageUrl = mImageUrl;
        this.mUser = mUser;
        this.mLikes = mLikes;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmUser() {
        return mUser;
    }

    public int getmLikes() {
        return mLikes;
    }
}
