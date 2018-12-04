package net.tsingk.persist;

import net.tsingk.pojo.Plant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by delin on 2018/6/21.
 */
@Service
public interface IPlantPersist {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "plantName", column = "PLANT_NAME"),
            @Result(property = "plantName1", column = "PLANT_NAME1"),
            @Result(property = "plantSummary", column = "PLANT_SUMMARY"),
            @Result(property = "pic1", column = "PIC1"),
    })
    @Select("SELECT id, PLANT_NAME, PLANT_NAME1, PLANT_SUMMARY, PIC1 FROM HYP_PLANTS LIMIT #{startPos},#{pageSize}")
    List<Plant> getPlantList(@Param("startPos") long startPos, @Param("pageSize") int pageSize);



    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "plantName", column = "PLANT_NAME"),
            @Result(property = "plantName1", column = "PLANT_NAME1"),
            @Result(property = "pic1", column = "PIC1"),
    })
    @Select("SELECT id, PLANT_NAME, PLANT_NAME1, PIC1 FROM HYP_REC_PLANTS LIMIT #{startPos},#{pageSize}")
    List<Plant> getRecPlantList(@Param("startPos") long startPos, @Param("pageSize") int pageSize);


    @Select("SELECT COUNT(*) FROM HYP_REC_PLANTS")
    long getRecPlantCount();


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "plantName", column = "PLANT_NAME"),
            @Result(property = "plantName1", column = "PLANT_NAME1"),
            @Result(property = "plantSummary", column = "PLANT_SUMMARY"),
            @Result(property = "pic1", column = "PIC1"),
    })
    @Select("SELECT id, PLANT_NAME, PLANT_NAME1, PLANT_SUMMARY, PIC1 FROM HYP_PLANTS WHERE id=#{id}")
    Plant getPlantShortInfo(long id);

    @Select("SELECT COUNT(*) FROM HYP_PLANTS")
    long getPlantCount();


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "plantName", column = "PLANT_NAME"),
            @Result(property = "plantName1", column = "PLANT_NAME1"),
            @Result(property = "plantSummary", column = "PLANT_SUMMARY"),
            @Result(property = "plantTemperture", column = "PLANT_TEMPERTURE"),
            @Result(property = "plantDesc", column = "PLANT_DESC"),
            @Result(property = "plantPlace", column = "PLANT_PLACE"),
            @Result(property = "planting", column = "PLANTING"),
            @Result(property = "likeWord", column = "LIKE_WORD"),
            @Result(property = "moisture", column = "MOISTURE"),
            @Result(property = "illumination", column = "ILLUMINATION"),
            @Result(property = "soil", column = "SOIL"),
            @Result(property = "plantation", column = "PLANTATION"),
            @Result(property = "introduction", column = "INTRODUCTION"),
            @Result(property = "word", column = "WORD"),
            @Result(property = "pic1", column = "PIC1"),
            @Result(property = "pic2", column = "PIC2"),
            @Result(property = "pic3", column = "PIC3"),
            @Result(property = "pic4", column = "PIC4"),
            @Result(property = "pic5", column = "PIC5"),
            @Result(property = "tRange1", column = "T_RANGE_1"),
            @Result(property = "tRange2", column = "T_RANGE_2"),
            @Result(property = "hRange1", column = "H_RANGE_1"),
            @Result(property = "hRange2", column = "H_RANGE_2"),
            @Result(property = "oRange1", column = "O_RANGE_1"),
            @Result(property = "oRange2", column = "O_RANGE_2"),
            @Result(property = "sRange1", column = "S_RANGE_1"),
            @Result(property = "sRange2", column = "S_RANGE_2"),
    })
    @Select("SELECT * FROM HYP_PLANTS WHERE id = #{id}")
    Plant getPlantById(long id);

    @Insert("INSERT INTO HYP_PLANTS(PLANT_NAME,PLANT_NAME1,PLANT_SUMMARY,PLANT_TEMPERTURE,PLANT_DESC,PLANT_PLACE,PLANTING,LIKE_WORD,MOISTURE,ILLUMINATION,SOIL,PLANTATION,INTRODUCTION," +
            "WORD,PIC1,PIC2,PIC3,PIC4,PIC5,T_RANGE_1,T_RANGE_2,H_RANGE_1,H_RANGE_2,O_RANGE_1,O_RANGE_2,S_RANGE_1,S_RANGE_2)" +
            "VALUES(#{plantName},#{plantName1},#{plantSummary},#{plantDesc},#{plantPlace},#{planting},#{likeWord},#{word},#{moisture},#{illumination},#{soil},#{plantation},#{introduction}," +
            "#{pic1},#{pic2},#{pic3},#{pic4},#{pic5},#{tRange1},#{tRange2},#{hRange1},#{hRange2},#{oRange1},#{oRange2},#{sRange1},#{sRange2})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertPlant(Plant plant);

    @Update("UPDATE HYP_PLANTS SET PLANT_NAME=#{plantName},PLANT_NAME1=#{plantName1},PLANT_SUMMARY=#{plantSummary},PLANT_TEMPERTURE=#{plantTemperture},PLANT_DESC=#{plantDesc},"+
            "PLANT_PLACE=#{plantPlace},PLANTING=#{planting},LIKE_WORD=#{likeWord},MOISTURE=#{moisture},ILLUMINATION=#{illumination},SOIL=#{soil},PLANTATION=#{plantation},INTRODUCTION=#{introduction},WORD=#{word},"+
            "PIC1=#{pic1},PIC2=#{pic2},PIC3=#{pic3},PIC4=#{pic4},PIC5=#{pic5},T_RANGE_1=#{tRange1},T_RANGE_2=#{tRange2},H_RANGE_1=#{hRange1},H_RANGE_2=#{hRange2},"+
            "O_RANGE_1=#{oRange1},O_RANGE_2=#{oRange2},S_RANGE_1=#{sRange1},S_RANGE_2=#{sRange2} WHERE id=#{id}")
    void updatePlant(Plant plant);


    @Update("UPDATE HYP_USER_PLANTS SET STATUS=1 WHERE DEVICE_HW_ID=#{hw_id}")
    void updateUserPlanting(@Param("hw_id") String hwId);

    @Update("UPDATE HYP_USER_PLANTS SET STATUS=1 WHERE DEVICE_HW_ID in (SELECT HW_ID FROM HYP_DEVICE WHERE MASTER_HWID=#{hw_id})")
    void updateUserPlantingByMasterDevice(@Param("hw_id") String hwId);
}
