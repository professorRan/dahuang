package net.tsingk.pojo;

import java.util.ArrayList;
import java.util.List;

public class Article {

    private int   articleId;

    private String title;

    private String subTitle;

    private String content;

    private List<String> tags;

    private List<ArticleAlbum> albums;

    private List<String> picList;

    private String articleContentPath;
    private String articleContextPath;

    private ArticleMeta meta;

    private  List<String> viewTags;


    private String albumsCtxPath;

    private String abbrTags;


    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void addTags(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    public void removeTags(String tag) {
        if (tags != null) {
            tags.remove(tag);
        }
        if (abbrTags != null) {
            abbrTags = abbrTags.replace(tag, "");
        }
    }


    public void addAlbum(ArticleAlbum aa) {
        if (albums == null) {
            albums = new ArrayList<>();
        }
        albums.add(aa);
    }


    public void removeAlbum(ArticleAlbum aa) {
        if (albums != null) {
            albums.remove(aa);
        }
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setAlbums(List<ArticleAlbum> albums) {
        this.albums = albums;
    }

    public List<ArticleAlbum> getAlbums() {
        return this.albums;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContentPath() {
        return articleContentPath;
    }

    public void setArticleContentPath(String articleContentPath) {
        this.articleContentPath = articleContentPath;
    }

    public String getContextPath() {
        return articleContextPath;
    }

    public void setArticleContextPath(String articleContextPath) {
        this.articleContextPath = articleContextPath;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }


    public ArticleMeta getMeta() {
        return meta;
    }

    public void setMeta(ArticleMeta meta) {
        this.meta = meta;
    }

    public List<String> getViewTags() {
        return viewTags;
    }

    public void setViewTags(List<String> viewTags) {
        this.viewTags = viewTags;
    }


    public String getAlbumsCtxPath() {
        return albumsCtxPath;
    }

    public void setAlbumsCtxPath(String path) {
        this.albumsCtxPath = path;
    }


    public void addAlbumsCtxPath(String path) {
        if (albumsCtxPath == null || albumsCtxPath.isEmpty()) {
            this.albumsCtxPath = path;
        } else {
            albumsCtxPath += albumsCtxPath + path +" ";
        }
    }

    public void removeAlbumsCtxPath(String path) {
        if (albumsCtxPath == null || albumsCtxPath.isEmpty()) {
            return;
        }
        albumsCtxPath = albumsCtxPath.replace(path +" ", "");
    }

    public String getAbbrTags() {
        return abbrTags;
    }

    public void setAbbrTags(String abbrTags) {
        this.abbrTags = abbrTags;
    }
}
