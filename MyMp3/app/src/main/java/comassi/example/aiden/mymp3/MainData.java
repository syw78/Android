package comassi.example.aiden.mymp3;

class MainData {

    private String id;    //아이디
    private String albumId; // 앨범아이디
    private String title; //텍스트뷰 title
    private String artist; //텍스트뷰 singer
    private boolean like; //이미지뷰 좋아요

    public MainData() {
    }

    public MainData(String id, String albumId, String title, String artist, boolean like) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.artist = artist;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
