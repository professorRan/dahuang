package net.tsingk.pojo;

import java.util.Objects;

public class ArticleAlbum {

    private String fileName;

    private String path;

    private String albumURL;

    private int width;

    private int height;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbumURL() {
        return albumURL;
    }

    public void setAlbumURL(String albumURL) {
        this.albumURL = albumURL;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAlbum that = (ArticleAlbum) o;
        return width == that.width &&
                height == that.height &&
                Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fileName, width, height);
    }
}
