package model;

public class Photo {
    public int albumId;
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;

    public Photo() {}

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (albumId != photo.albumId) return false;
        if (id != photo.id) return false;
        if (!title.equals(photo.title)) return false;
        if (!url.equals(photo.url)) return false;
        return thumbnailUrl.equals(photo.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + id;
        result = 31 * result + title.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + thumbnailUrl.hashCode();
        return result;
    }
}
