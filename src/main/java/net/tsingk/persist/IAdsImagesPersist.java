package net.tsingk.persist;

import java.util.List;

import net.tsingk.pojo.AdsImages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Service
public interface IAdsImagesPersist {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "contextPath", column = "CONTEXT_PATH"),
            @Result(property = "imageWidth", column = "IMAGE_WIDTH"),
            @Result(property = "imageHeight", column = "IMAGE_HEIGHT"),
            @Result(property = "adsType", column = "ADS_TYPE"),
            @Result(property = "adsLink", column = "ADS_LINK"),

    })
    @Select("SELECT id, CONTEXT_PATH, IMAGE_WIDTH, IMAGE_HEIGHT, ADS_TYPE,ADS_LINK FROM HYP_ADS_IMAGES")
    List<AdsImages> getAdsImageList();


    @Insert("INSERT INTO HYP_ADS_IMAGES(CONTEXT_PATH,IMAGE_WIDTH,IMAGE_HEIGHT,ADS_TYPE,ADS_LINK)" +
            "VALUES(#{contextPath},#{imageWidth},#{imageHeight},#{adsType},#{adsLink})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertAdsImage(AdsImages adsImage);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "contextPath", column = "CONTEXT_PATH"),
            @Result(property = "imageWidth", column = "IMAGE_WIDTH"),
            @Result(property = "imageHeight", column = "IMAGE_HEIGHT"),
            @Result(property = "adsType", column = "ADS_TYPE"),
            @Result(property = "adsLink", column = "ADS_LINK"),

    })
    @Select("SELECT id, CONTEXT_PATH, IMAGE_WIDTH, IMAGE_HEIGHT,ADS_LINK FROM HYP_ADS_IMAGES WHERE id=#{id}")
    AdsImages getImage(int id);


    @Delete("DELETE FROM HYP_ADS_IMAGES WHERE id=#{id}")
    void delete(int id);
}
