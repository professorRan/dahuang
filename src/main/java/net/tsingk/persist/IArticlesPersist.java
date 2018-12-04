package net.tsingk.persist;

import net.tsingk.pojo.Article;
import net.tsingk.pojo.ArticleAlbum;
import net.tsingk.pojo.ArticleMeta;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IArticlesPersist {


    @Insert("INSERT INTO HYP_ARTICLE(TITLE, SUB_TITLE, VIEW_TAGS, VIEW_COUNT, CREATE_TIME, LAST_UPDATE, CONTENT_PATH, ALBUM_CTX_PATH, TAGS) VALUES(#{art.title}, #{art.subTitle}, #{meta.viewTags}, #{meta.viewCount}, #{meta.createTime}, #{meta.lastUpdate}, #{art.contextPath}, #{art.albumsCtxPath}, #{art.abbrTags})")
    @Options(useGeneratedKeys = true, keyProperty = "art.articleId", keyColumn = "id")
    void insertArticle(@Param("art") Article art, @Param("meta") ArticleMeta meta);


    @Insert("INSERT INTO HYP_ARTICLE_TAG(ARTICLE_ID, TAG) values(#{art.articleId}, #{tag})")
    void insertArticleTags(@Param("art") Article art, @Param("tag") String tag);


    @Insert("INSERT INTO HYP_ARTICLE_ALBUM(ARTICLE_ID, CONTEXT_PATH, WIDTH, HEIGHT) values(#{art.articleId}, #{aa.path}, #{aa.width}, #{aa.height})")
    void insertArticleAlbum(@Param("art") Article art, @Param("aa") ArticleAlbum aa);


    @Update("UPDATE HYP_ARTICLE SET LAST_UPDATE=#{meta.lastUpdate} where ID=#{art.articleId}")
    void updateArticleVersion(@Param("art") Article art, @Param("meta") ArticleMeta meta);


    @Update("UPDATE HYP_ARTICLE SET TITLE=#{title}, CONTENT_PATH=#{contextPath}, VIEW_TAGS=#{view_tags}, LAST_UPDATE=CURRENT_TIMESTAMP() where ID=#{articleId}")
    void updateArticleContent(@Param("articleId") long id, @Param("title") String title, @Param("view_tags") String viewTags, @Param("contextPath") String path);


    @Update("UPDATE HYP_ARTICLE SET ALBUM_CTX_PATH=#{albums_path}, LAST_UPDATE=CURRENT_TIMESTAMP() where ID=#{articleId}")
    void updateArticleAlbumCache(@Param("articleId") long id, @Param("albums_path") String albumPaths);


    @Update("UPDATE HYP_ARTICLE SET TAGS=#{tags}, LAST_UPDATE=CURRENT_TIMESTAMP() where ID=#{articleId}")
    void updateArticleTagsCache(@Param("articleId") long id, @Param("tags") String tags);


    @Delete("DELETE FROM HYP_ARTICLE WHERE ARTICLE_ID = #{art.articleId}")
    void deleteArticle(@Param("art") Article art);

    @Delete("DELETE FROM HYP_ARTICLE_ALBUM where ARTICLE_ID = #{art.articleId}")
    void deleteArticleAlbum(@Param("art") Article art);

    @Delete("DELETE FROM HYP_ARTICLE_TAG where ARTICLE_ID = #{art.articleId}")
    void deleteArticleTag(@Param("art") Article art);


    @Delete("DELETE FROM HYP_ARTICLE_ALBUM where  CONTEXT_PATH=#{aa.path}")
    void removeArticleAlbum(@Param("art") Article art, @Param("aa") ArticleAlbum aa);

    @Delete("DELETE FROM HYP_ARTICLE_TAG where ARTICLE_ID = #{art.articleId} and tag = #{tag}ß")
    void removeArticleTag(@Param("art") Article art, @Param("tag") String tag);


    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "viewTags", column = "VIEW_TAGS"),
            @Result(property = "viewCount", column = "VIEW_COUNT"),
            @Result(property = "createTime", column = "CREATE_TIME"),
            @Result(property = "lastUpdate", column = "LAST_UPDATE"),
    })
    @Select("SELECT ID, VIEW_TAGS, VIEW_COUNT, CREATE_TIME, LAST_UPDATE FROM HYP_ARTICLE WHERE id=#{id}")
    ArticleMeta getArticleMetaShortInfo(long id);


    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "subTitle", column = "SUB_TITLE"),
            @Result(property = "articleContextPath", column = "CONTENT_PATH"),
            @Result(property = "albumsCtxPath", column = "ALBUM_CTX_PATH"),
            @Result(property = "abbrTags", column = "TAGS"),
            @Result(property = "meta.viewTags", column = "VIEW_TAGS"),
            @Result(property = "meta.viewCount", column = "VIEW_COUNT"),
            @Result(property = "meta.createTime", column = "CREATE_TIME"),
            @Result(property = "meta.lastUpdate", column = "LAST_UPDATE"),
    })
    @Select("SELECT ID, TITLE, SUB_TITLE, VIEW_TAGS, VIEW_COUNT, CREATE_TIME, LAST_UPDATE, CONTENT_PATH, ALBUM_CTX_PATH,TAGS FROM HYP_ARTICLE  WHERE id=#{id}")
    Article getArticleInfo(long id);


    @Select("SELECT COUNT(ID) FROM HYP_ARTICLE")
    long getArticleCount();


    @Results({
            @Result(property = "articleId", column = "id"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "subTitle", column = "SUB_TITLE"),
            @Result(property = "meta.viewTags", column = "VIEW_TAGS"),
            @Result(property = "articleContextPath", column = "CONTENT_PATH"),
            @Result(property = "albumsCtxPath", column = "ALBUM_CTX_PATH"),
            @Result(property = "meta.viewCount", column = "VIEW_COUNT"),
            @Result(property = "meta.createTime", column = "CREATE_TIME"),
            @Result(property = "meta.lastUpdate", column = "LAST_UPDATE"),
    })
    @Select("SELECT ID, TITLE, SUB_TITLE, VIEW_TAGS, VIEW_COUNT, CREATE_TIME, LAST_UPDATE, CONTENT_PATH,ALBUM_CTX_PATH,TAGS  FROM HYP_ARTICLE LIMIT #{startPos},#{pageSize}")
    List<Article> getArticleList(@Param("startPos") long startPos, @Param("pageSize") int pageSize);

    @Select("SELECT TAG FROM HYP_ARTICLE_TAG WHERE ARTICLE_ID = #{id}")
    List<String> getArticleTags(@Param("id") long artId);


    @Results({
            @Result(property = "path", column = "CONTEXT_PATH"),
            @Result(property = "width", column = "WIDTH"),
            @Result(property = "height", column = "HEIGHT")
    })
    @Select("SELECT CONTEXT_PATH,WIDTH,HEIGHT FROM HYP_ARTICLE_ALBUM WHERE ARTICLE_ID = #{id}")
    List<ArticleAlbum> getArticleAlbums(@Param("id") long artId);

}
